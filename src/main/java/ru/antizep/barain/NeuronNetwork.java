package ru.antizep.barain;

import ru.antizep.error.InputsCountError;

import java.util.LinkedList;

public class NeuronNetwork {

    LinkedList<Neuron[]> slices = new LinkedList<>();

    public void addInputSlice(int countNeurons, int countSignals) {
        slices.add(Neuron.generateNeiron(countNeurons, countSignals));
    }

    public void addHiddenSlice(int countNeuron) {
        Neuron[] previous = slices.getLast();
        slices.add(Neuron.generateNeiron(countNeuron, previous.length));
    }

    public double[] getResult(double[] signals) throws InputsCountError {
        double[] exitSignals = signals;
        for (Neuron[] slice:slices) {
            exitSignals = getExits(slice,exitSignals);
        }
        return exitSignals;
    }

    public void toLearn(double[] targetData){
        calculateExitErrors(targetData);
        for (int i = slices.size()-2; i >=0 ; i--) {
            calculateHiddenErrors(slices.get(i+1),slices.get(i));
        }
        for (Neuron[] slice:slices) {
            learn(slice);
        }
    }


    static void learn(Neuron[] slice){
        for (Neuron neuron : slice) {
            neuron.updateWeightsAndSlip();
        }
    }

    private void calculateExitErrors(double[] targetData) {
        Neuron[] exitNeurons =  slices.getLast();
        for (int i = 0; i < exitNeurons.length; i++) {
            exitNeurons[i].calculateError(targetData[i]);
            //System.out.println(neurons[i].getError());
        }
    }

    private void calculateHiddenErrors(Neuron[] previousSlice, Neuron[] hiddenSlice) {
        for (int i = 0; i < hiddenSlice.length; i++) {
            double[] errors = new double[hiddenSlice.length];
            for (Neuron previousNeuron: previousSlice) {
                errors[i] = previousNeuron.getInputErrors()[i];
            }
            hiddenSlice[i].calculateError(errors);
        }
    }

    static double[] getExits(Neuron[] slice, double[] signals) throws InputsCountError {
        double[] exits = new double[slice.length];
        for (int i = 0; i < exits.length; i++) {
            exits[i] = slice[i].getExit(signals);
        }
        return exits;
    }
}
