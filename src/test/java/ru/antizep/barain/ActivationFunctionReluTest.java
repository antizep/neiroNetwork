package ru.antizep.barain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.antizep.activation.ActivationFunctionRelu;

import static org.junit.jupiter.api.Assertions.*;

class ActivationFunctionReluTest {

    ActivationFunctionRelu relu;

    @BeforeEach
    void startup() {
        relu = new ActivationFunctionRelu();
    }

    @Test
    void active() {
        assertEquals(100, relu.activation(100));
    }
    @Test
    void inactive(){
        assertEquals(0,relu.activation(-99));
    }
}