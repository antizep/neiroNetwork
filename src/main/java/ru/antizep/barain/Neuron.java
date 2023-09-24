package ru.antizep.barain;

import org.json.JSONPropertyIgnore;
import ru.antizep.Mathematics;
import ru.antizep.MathematicsImpl;
import ru.antizep.activation.ActivationFunction;
import ru.antizep.activation.ActivationFunctionSig;
import ru.antizep.error.InputsCountError;

public class Neuron {
    public static final double learningSpeed = 0.01;
    private double[] weights;
    private double slip = Math.random();

    private Mathematics mathematics = new MathematicsImpl();
    private ActivationFunction activationFunction;
    private double exit = 0;
    private double error = 0;
    private double[] inputErrors;
    private double[] lastInputs;

    public static Neuron[] generateNeiron(int count, int countInputSignal) {
        Neuron[] neurons = new Neuron[count];
        for (int i = 0; i < count; i++) {
            neurons[i] = new Neuron(countInputSignal);
        }
        return neurons;
    }

    public Neuron(int countSignal) {
        weights = generateRandomArray(countSignal);
        activationFunction = new ActivationFunctionSig();
    }

    public static double[] generateRandomArray(int count) {
        double[] randoms = new double[count];
        for (int i = 0; i < count; i++) {
            randoms[i] = Math.random();
        }
        return randoms;
    }

    public double getExit(double[] inputs) throws InputsCountError {
        lastInputs = inputs;
        validate(inputs);
        double[] res = mathematics.multipleArrays(inputs, weights);
        double summ = mathematics.summArray(res);
        double sliped = summ + slip;
        exit = activationFunction.activation(sliped);
        return exit;

    }

    private void validate(double[] inputs) throws InputsCountError {
        if (inputs.length != weights.length)
            throw new InputsCountError("Количество входящих сигналов не соответствует количеству проинициализированых весов");
    }


    public void updateWeightsAndSlip() {
        for (int i = 0; i < lastInputs.length; i++) {
            double coof = lastInputs[i] * error * learningSpeed;
            weights[i] = weights[i] - coof;
        }
        slip = slip - error*learningSpeed;
    }

    /**
     * вычислкение влияния ошибки с каждого входа
     */
    private void calculateErrorInput() {
        inputErrors = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            inputErrors[i] = error * weights[i];
        }
    }

    /**
     * @param actual правильное значение
     */
    public void calculateError(double actual) {
//        double error = Math.pow(exit - actual, 2) / 2;
       double error = exit - actual;
        calculateError(new double[]{error});

    }

    /**
     * @param errors массив влиния на зависимые нейроны
     */
    public void calculateError(double... errors) {
        error = mathematics.summArray(errors) * activationFunction.calcDerivativeFunction(exit);
        calculateErrorInput();
    }
    @JSONPropertyIgnore
    public double[] getInputErrors() {
        return inputErrors;
    }

    @JSONPropertyIgnore
    public double getError() {
        return error;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getSlip() {
        return slip;
    }
}
