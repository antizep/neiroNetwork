package ru.antizep.barain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.antizep.error.InputsCountError;

import static org.junit.jupiter.api.Assertions.*;

class NeironTest {
    public static final int countSignal = 10;
    private Neiron neiron;
    @BeforeEach
    void setUp() {
        neiron = new Neiron(10);
    }

    @Test
    void invalidCountSignal() {
        assertThrows(InputsCountError.class,()->neiron.getExit(new double[]{}));
    }
    @Test
    void validDataDump(){
        assertDoesNotThrow(()->neiron.getExit(new double[countSignal]));
    }
}