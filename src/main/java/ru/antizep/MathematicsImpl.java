package ru.antizep;

public class MathematicsImpl implements Mathematics {
    @Override
    public double[] multipleArrays(double[] a, double[] b) {
        double[] res = new double[a.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = a[i]*b[i];
        }
        return  res;
    }

    @Override
    public double summArray(double[] a) {
        double res = 0;
        for (int i = 0; i < a.length; i++) {
            res+=a[i];
        }
        return res;
    }
}
