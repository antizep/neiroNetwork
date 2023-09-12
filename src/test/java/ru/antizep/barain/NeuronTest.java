package ru.antizep.barain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.antizep.error.InputsCountError;

import static org.junit.jupiter.api.Assertions.*;

class NeuronTest {
    public static final int countSignal = 10;
    private Neuron neuron;
    @BeforeEach
    void setUp() {
        neuron = new Neuron(10);
    }

    @Test
    void invalidCountSignal() {
        assertThrows(InputsCountError.class,()-> neuron.getExit(new double[]{}));
    }
    @Test
    void validDataDump(){
        assertDoesNotThrow(()-> neuron.getExit(new double[countSignal]));
    }
}