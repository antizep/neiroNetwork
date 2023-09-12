package ru.antizep.activation;

import ru.antizep.Main;

public class ActivationFunctionSig implements ActivationFunction {
    @Override
    public double activation(double input) {
        return 1/(1+Math.pow(Math.E,-input));

    }

    @Override
    public double calcDerivativeFunction(double input) {
        double prev = 1/(1+Math.pow(Math.E,-input));
        return prev*(1-prev);
    }
}
