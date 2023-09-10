package ru.antizep.activation;

public class ActivationFunctionRelu implements ActivationFunction {


    @Override
    public double activation(double input) {
        if (input >= 0 ){
            return input;
        }else
            return 0;
    }

    @Override
    public double calcDerivativeFunction(double input) {
        if(input<0){
            return 0;
        }else {
            return 1;
        }
    }
}
