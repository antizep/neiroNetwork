package ru.antizep.sound;

import java.util.List;

public class DoubleByteConverter {

    public static Byte[] doublesToBytes(double[] doubles) {
        Byte[] bytes = new Byte[doubles.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = doubleToByte(doubles[i]);
        }
        return bytes;
    }

    public static double[] bytesToDoubles(List<Byte> bytes) {
        double[] doubles = new double[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            doubles[i] = byteToDouble(bytes.get(i));
        }
        return doubles;
    }

    public static byte doubleToByte(double d) {
        return (byte) (-128 + d * (127 + 128));
    }

    public static double byteToDouble(byte b) {
        return (b + 128F) / (127F + 128F);
    }
}
