package ru.antizep.barain;

import ru.antizep.Mathematics;
import ru.antizep.MathematicsImpl;
import ru.antizep.activation.ActivationFunction;
import ru.antizep.activation.ActivationFunctionRelu;
import ru.antizep.error.InputsCountError;

public class Neiron {
    public static final double learningSpeed =  0.5;
    private double[] weigths;
    private double slip = Math.random();
    private Mathematics mathematics = new MathematicsImpl();

    private ActivationFunction activationFunction;
    private double exit = 0;

    public static Neiron[] generateNeiron(int count, int countInputSignal){
        Neiron[] neirons = new Neiron[count];
        for (int i = 0; i < count; i++) {
            neirons[i] = new Neiron(countInputSignal);
        }
        return neirons;
    }
    public Neiron(int countSignal) {
        weigths = generateRandomArray(countSignal);
        activationFunction = new ActivationFunctionRelu();
    }
    public static double[] generateRandomArray(int count){
        double[] randoms = new double[count];
        for (int i = 0; i < count; i++) {
            randoms[i] = Math.random();
        }
        return randoms;
    }

    public double getExit(double[] inputs) throws InputsCountError {
        validate(inputs);
        double[] res = mathematics.multipleArrays(inputs,weigths);
        double summ = mathematics.summArray(res);
        double sliped = summ+slip;
        exit = activationFunction.activation(sliped);
        return exit;

    }
    private void validate(double[] inputs) throws InputsCountError {
        if (inputs.length!=weigths.length) throw new InputsCountError("Количество входящих сигналов не соответствует количеству проинициализированых весов");
    }


    public void fixError(double error){

    }

    public void  fixError(double actual, double exit){
        double errorLocal = getError(actual,exit);
        fixError(errorLocal);
    }

    /** вычислкение влияния ошибки с каждого входа
     * @param errorLocal значение ошибки
     * @return массив влияния ошибок для каждого входа
     */
    private double[] calculateError(double errorLocal){
        double[] errors = new double[weigths.length];


        for (int i = 0; i < weigths.length; i++) {
            errors[i] = errorLocal * weigths[i];
        }
        return errors;
    }

    /**
     * @param actual правильное значение
     * @return средняя квадратичная ошибка выхода конечного нейрона.
     */
    private double getError(double actual){
        double error = Math.pow(exit-actual,2)/2;
        return getError(new double[]{error});
    }

    /**
     * @param errors массив влиния на зависимые нейроны
     * @return ошибка для скрытых слоев.
     */
    private double getError(double... errors){
        return mathematics.summArray(errors)*activationFunction.calcDerivativeFunction(exit);
    }

}
