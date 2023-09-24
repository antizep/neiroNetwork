package ru.antizep;

import ru.antizep.barain.Neuron;
import ru.antizep.barain.NeuronNetwork;
import ru.antizep.error.InputsCountError;
import ru.antizep.sound.DoubleByteConverter;
import ru.antizep.sound.SoundEditor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static double[] data = Neuron.generateRandomArray(512);
    static double[] targetData = Neuron.generateRandomArray(512);

    public static void main(String[] args) throws InputsCountError, IOException, URISyntaxException {

        System.out.println("start");
        NeuronNetwork neuronNetwork = new NeuronNetwork();
        neuronNetwork.addInputSlice(512, 512);

        neuronNetwork.addHiddenSlice(256);

        neuronNetwork.addHiddenSlice(512);

        byte[] original = SoundEditor.loadSound("/home/antizep/git/neiro/kall_goblin.wav");
        byte[] my = SoundEditor.loadSound("/home/antizep/git/neiro/my.wav");
        int epoch = 0;
        List<Byte> totalConverted = new ArrayList<>();
        while (epoch < 100) {
            totalConverted.clear();
            List<Byte> originalPart = new ArrayList<>();
            List<Byte> myPart = new ArrayList<>();
            for (int i = 0; i < my.length; i++) {
                originalPart.add(original[i]);
                myPart.add(my[i]);
                if (originalPart.size() == 512) {
                    var originalDouble = DoubleByteConverter.bytesToDoubles(originalPart);
                    var myDouble = DoubleByteConverter.bytesToDoubles(myPart);

                    var converted = convertTrain(originalDouble, myDouble, neuronNetwork);
                    totalConverted.addAll(List.of(DoubleByteConverter.doublesToBytes(converted)));
                    originalPart.clear();
                    myPart.clear();
                }
            }
            System.out.println("epoch:" + epoch + " error:" + neuronNetwork.error());

            SoundEditor.saveSound(SoundEditor.byteToPrimitive(totalConverted), "converted_"+epoch+".wav");
            epoch++;

        }
        byte[] newMy = SoundEditor.loadSound("/home/antizep/git/neiro/MyNew.wav");
        totalConverted.clear();
        List<Byte> originalPart = new ArrayList<>();
        for (byte b : newMy) {
            originalPart.add(b);
            if (originalPart.size() == 512) {
                var originalDouble = DoubleByteConverter.bytesToDoubles(originalPart);
                var converted = convert(originalDouble, neuronNetwork);
                totalConverted.addAll(List.of(DoubleByteConverter.doublesToBytes(converted)));
                originalPart.clear();
            }
        }

        SoundEditor.saveSound(SoundEditor.byteToPrimitive(totalConverted), "converted_" + epoch + ".wav");
    }

    static double[] convert(double[] original, NeuronNetwork neuronNetwork) throws InputsCountError {
        double[] result = neuronNetwork.getResult(original);
        return result;
    }

    static double[] convertTrain(double[] originalPart, double[] myPart, NeuronNetwork neuronNetwork) throws InputsCountError {

        double[] result = neuronNetwork.getResult(myPart);
        neuronNetwork.toLearn(originalPart);
        return result;
    }

}