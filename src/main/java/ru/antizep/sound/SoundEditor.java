package ru.antizep.sound;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SoundEditor {

    private static final String endHeader = "data";

    public static byte[] loadSound(String path) throws IOException, URISyntaxException {
        byte[] result;
        byte[] soundByte = Files.readAllBytes(Path.of(path));
        result = cleanSound(soundByte);
        return result;
    }

    private static byte[] cleanSound(byte[] soundBytes) throws IOException {
        return removeHeader(soundBytes);
    }

    private static byte[] removeHeader(byte[] soundBytes) throws IOException {
        List<Byte> headerByte = new ArrayList<>();
        List<Byte> dataBytes = new ArrayList<>();

        boolean searshed = false;
        for (byte b : soundBytes) {
            if (!searshed) {
                searshed = isEndHeader(headerByte);
                headerByte.add(b);
                saveFile(byteToPrimitive(headerByte), "header.wav");
            } else {
                dataBytes.add(b);
            }
        }
        return byteToPrimitive(dataBytes);
    }

    private static boolean isEndHeader(List<Byte> soundByte) {
        int lastIndex = soundByte.size() - 1;
        if (lastIndex < 3) {
            return false;
        }

        return soundByte.get(lastIndex) == "a".getBytes()[0] &&
                soundByte.get(lastIndex - 1) == "t".getBytes()[0] &&
                soundByte.get(lastIndex - 2) == "a".getBytes()[0] &&
                soundByte.get(lastIndex - 3) == "d".getBytes()[0];
    }

    public static void saveFile(byte[] soundByte, String path) throws IOException {
        Files.write(Path.of(path), soundByte);
    }
    public static void saveSound(byte[] bytes, String path) throws IOException {
        byte[] headerByte = Files.readAllBytes(Path.of("header.wav"));
        ArrayList<Byte> a = new ArrayList<>();
        for (byte byteB : headerByte) {
            a.add(byteB);
        }
        for (byte byteB: bytes) {
            a.add(byteB);
        }
        saveFile(byteToPrimitive(a),path);
    }

    public static byte[] byteToPrimitive(List<Byte> bytes) {
        int arraySize = bytes.size();
        byte[] bs = new byte[arraySize];
        for (int i = 0; i < arraySize; i++) {
            bs[i] = bytes.get(i);
        }
        return bs;
    }
}
