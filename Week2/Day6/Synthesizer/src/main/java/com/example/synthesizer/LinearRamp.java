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
        AudioClip clip = new AudioClip();
        for (int i = 0; i < numSamples; i++) {
            float t = (float) i / numSamples;
            int sampleValue = (int) ((start * (numSamples - i) + stop * i) / (float) numSamples);
            clip.setSample(i, sampleValue);
        }
        return clip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        // 连接输入（如果需要）
    }
}
