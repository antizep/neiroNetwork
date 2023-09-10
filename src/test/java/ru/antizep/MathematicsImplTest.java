package ru.antizep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathematicsImplTest {

    MathematicsImpl mathematics;
    double[] a = new double[] {0.01,0.94,0.0001};
    double[] b = new double[] {1,1,1};
    @BeforeEach
    void setUp() {
        mathematics = new MathematicsImpl();
    }

    @Test
    void multipleArrays() {

        assertArrayEquals(a,mathematics.multipleArrays(b,a));
    }

    @Test
    void summArray() {
        assertEquals(3,mathematics.summArray(b));
    }
}