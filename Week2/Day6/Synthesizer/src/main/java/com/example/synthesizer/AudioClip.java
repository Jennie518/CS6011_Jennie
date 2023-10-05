package com.example.synthesizer;

import java.util.Arrays;
public class AudioClip {
    // Constants
    public static final double DURATION = 2.0;    // Duration in seconds
    public static final int SAMPLE_RATE = 44100;  // Sample rate in Hz
    public static final int TOTAL_SAMPLES = (int) (DURATION * SAMPLE_RATE );

    // Member variables
    private final byte[] data;

    // Constructor
    public AudioClip() {
        // Calculate the length of the byte array
        int length = (int) (DURATION * SAMPLE_RATE * 2);  // 2 bytes per sample (16 bits)
        data = new byte[length];
    }

    // Getters and setters
    public int getSample(int index) {
        // Ensure index is valid
        if (index < 0 || index >= data.length / 2) {
            throw new IllegalArgumentException("Invalid index");
        }

        // Retrieve the lower and upper 8 bits
        int lowerByte = data[2 * index] & 0xFF;
        int upperByte = data[2 * index + 1] & 0xFF;

        // Combine and return as a signed short
        return (short) ((upperByte << 8) | lowerByte);
    }

    public void setSample(int index, int value) {
        // Ensure index is valid
        if (index < 0 || index >= data.length / 2) {
            throw new IllegalArgumentException("Invalid index");
        }

        // Convert the signed short value to bytes (little-endian)
        data[2 * index] = (byte) (value & 0xFF);          // Lower 8 bits
        data[2 * index + 1] = (byte) ((value >> 8) & 0xFF);  // Upper 8 bits
    }

    public byte[] getData() {
        // Return a copy of the data to avoid aliasing issues
        return Arrays.copyOf(data, data.length);
    }
    public void setData(byte[] newData) {
//        int lowerByte = data[2 * i] & 0xFF;
//        int upperByte = data[2 * i + 1] & 0xFF;
        // 检查新数据的长度是否与现有数据长度相同
        if (newData.length != data.length) {
            throw new IllegalArgumentException("New data length must match current data length");
        }

        // 将新数据复制到内部数据数组
        System.arraycopy(newData, 0, data, 0, data.length);
    }

}
