package com.example.synthesizer;

public class VFSineWave implements AudioComponent {
    private final float maxValue;
    private final float sampleRate;
    private final AudioComponent frequencyInput;

    public VFSineWave(float maxValue, float sampleRate, AudioComponent frequencyInput) {
        this.maxValue = maxValue;
        this.sampleRate = sampleRate;
        this.frequencyInput = frequencyInput;
    }

    @Override
    public AudioClip getClip() {
        int numSamples = AudioClip.TOTAL_SAMPLES;
        float[] samples = new float[numSamples];

        float phase = 0.0f;

        for (int i = 0; i < numSamples; i++) {
            float frequency = frequencyInput.getClip().getSample(i);
            phase += 2 * Math.PI * frequency / sampleRate;
            samples[i] = maxValue * (float) Math.sin(phase);
        }

        AudioClip clip = new AudioClip();
        return clip;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input, int index) {
        throw new UnsupportedOperationException("VFSineWave does not accept direct input.");
    }

}



