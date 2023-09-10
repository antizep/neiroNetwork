package ru.antizep;

import ru.antizep.barain.Neiron;
import ru.antizep.error.InputsCountError;

public class Main {
    static double[] data = Neiron.generateRandomArray(10);
    static double[] targetData = Neiron.generateRandomArray(10);

    public static void main(String[] args) throws InputsCountError {
        System.out.println("start");
        Neiron[] inputSlice = Neiron.generateNeiron(10, 10);
        Neiron[] hidden1Slice = Neiron.generateNeiron(20, 10);
        Neiron[] hidden2Slice = Neiron.generateNeiron(20, 20);
        Neiron[] exitSlice = Neiron.generateNeiron(10, 20);

        double[] inputSliceExits = getExits(inputSlice, data);
        double[] hidden1SliceExits = getExits(hidden1Slice,inputSliceExits);
        double[] hidden2SliceExits = getExits(hidden2Slice,hidden1SliceExits);
        double[] exitSliceExits = getExits(exitSlice,hidden2SliceExits);

        System.out.println("final");
    }

    static double[] getExits(Neiron[] slice, double[] signals) throws InputsCountError {
        double[] exits = new double[slice.length];
        for (int i = 0; i < exits.length; i++) {
            exits[i] = slice[i].getExit(signals);
        }
        return exits;
    }
}