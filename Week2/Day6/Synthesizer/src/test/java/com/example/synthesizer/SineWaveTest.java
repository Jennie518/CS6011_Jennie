//package com.example.synthesizer;
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SineWaveTest {
//
//    @Test
//    public void testGenerateSineWaveClip() {
//        double frequency = 440.0; // Frequency of A note (440 Hz)
//        AudioClip sineWaveClip = generateSineWaveClip(frequency, Short.MAX_VALUE);
//
//        // Check if the sine wave clip is not null
//        assertNotNull(sineWaveClip);
//
//        // Check the length of the generated clip
//        int expectedLength = (int) (AudioClip.DURATION * AudioClip.SAMPLE_RATE);
//        assertEquals(expectedLength, sineWaveClip.getData().length / 2);
//
//        // Check the first few samples to see if they are within a reasonable range
//        short[] samples = new short[expectedLength];
//        for (int i = 0; i < expectedLength; i++) {
//            samples[i] = (short) sineWaveClip.getSample(i);
//            assertTrue(samples[i] >= Short.MIN_VALUE && samples[i] <= Short.MAX_VALUE);
//        }
//
//        // Check if the frequency of the sine wave matches the expected frequency
//        int sampleRate = AudioClip.SAMPLE_RATE;
//        double calculatedFrequency = calculateFrequency(samples, sampleRate);
//        assertEquals(frequency, calculatedFrequency, 0.01); // Allowing a small error margin
//    }
//
//    // Calculate the frequency of a sine wave from its samples and sample rate
//    private double calculateFrequency(short[] samples, int sampleRate) {
//        int maxIndex = 0;
//        short maxSample = 0;
//
//        // Find the index of the maximum sample value
//        for (int i = 0; i < samples.length; i++) {
//            if (Math.abs(samples[i]) > maxSample) {
//                maxSample = (short) Math.abs(samples[i]);
//                maxIndex = i;
//            }
//        }
//        // Calculate the frequency using the index of the maximum sample
//        return (double) maxIndex * sampleRate / samples.length;
//    }
//
//    // Define the generateSineWaveClip method as described earlier
//    private AudioClip generateSineWaveClip(double frequency, short maxValue) {
//        // Implementation of generateSineWaveClip method goes here...
//        // For testing purposes, you can return a dummy AudioClip
//        return new AudioClip();
//    }
//}