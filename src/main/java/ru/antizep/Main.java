package ru.antizep;

import ru.antizep.barain.Neuron;
import ru.antizep.barain.NeuronNetwork;
import ru.antizep.error.InputsCountError;

import java.util.Arrays;

public class Main {
    static double[] data = Neuron.generateRandomArray(512);
    static double[] targetData = Neuron.generateRandomArray(512);

    public static void main(String[] args) throws InputsCountError {
        System.out.println("start");
        NeuronNetwork neuronNetwork = new NeuronNetwork();
        neuronNetwork.addInputSlice(512,512);
        neuronNetwork.addHiddenSlice(1028);
        neuronNetwork.addHiddenSlice(512);

        double[] res;
        int i = 0;
        while (i <= 5000) {
            res =  neuronNetwork.getResult(data);
            neuronNetwork.toLearn(targetData);
            System.out.println("res:  "+Arrays.toString(res));
            System.out.println("tgt:  "+ Arrays.toString(targetData));
            i++;
        }

    }
}