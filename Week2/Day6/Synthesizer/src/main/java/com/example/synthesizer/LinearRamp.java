package com.example.synthesizer;

public class LinearRamp implements AudioComponent {
    private final int start;
    private final int stop;

    public LinearRamp(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    @Override
    public AudioClip getClip() {
        int numSamples = AudioClip.TOTAL_SAMPLES;
        float[] samples = new float[numSamples];

        for (int i = 0; i < numSamples; i++) {
            float t = (float) i / numSamples;
            samples[i] = (start * (numSamples - i) + stop * i) / (float) numSamples;
        }

        AudioClip clip = new AudioClip();
        return clip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input, int index) {

    }

    @Override
    public void connectInput(AudioComponent input) {
        throw new UnsupportedOperationException("LinearRamp does not accept input.");
    }
}
