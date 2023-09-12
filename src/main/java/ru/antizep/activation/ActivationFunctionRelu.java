package ru.antizep.activation;

public class ActivationFunctionRelu implements ActivationFunction {

    double slice = 0.01;
    double max = 255;
    @Override
    public double activation(double input) {
        if (input>255){
            return max+input*slice;
        }
        if (input >= 0 ){
            return input;
        }else
            return 0 +input*slice;
    }

    @Override
    public double calcDerivativeFunction(double input) {
        if(input<0){
            return 0.01;
        }else {
            return 1;
        }
    }
}
