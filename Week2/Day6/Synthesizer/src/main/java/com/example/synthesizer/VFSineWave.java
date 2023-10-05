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
        AudioClip clip = new AudioClip();

        float phase = 0.0f;

        for (int i = 0; i < numSamples; i++) {
            phase += 2 * Math.PI * frequencyInput.getClip().getSample(i) / sampleRate;
            int sampleValue = (int) (32767 * maxValue * Math.sin(phase));
            clip.setSample(i, sampleValue);
        }

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

//public class VFSineWave implements AudioComponent {
//    private final float maxValue;
//    private final float sampleRate;
//    private final AudioComponent frequencyInput;
//
//    public VFSineWave(float maxValue, float sampleRate, AudioComponent frequencyInput) {
//        this.maxValue = maxValue;
//        this.sampleRate = sampleRate;
//        this.frequencyInput = frequencyInput;
//    }
//
//    @Override
//    public AudioClip getClip() {
//        int numSamples = AudioClip.TOTAL_SAMPLES;
//        float[] samples = new float[numSamples];
//        float phase = 0.0f;
//        for (int i = 0; i < numSamples; i++) {
////            frequencyInput.getClip().getSample(i);
////            System.out.println(frequency)
//            phase += 2 * Math.PI * frequencyInput.getClip().getSample(i) / sampleRate;
//            samples[i] = maxValue * (float) Math.sin(phase);
//            clip.setSample(i, (int) samples[i]);
//        }
//        AudioClip clip = new AudioClip();
//        return clip;
//    }
//    @Override
//    public boolean hasInput() {
//        return true;
//    }
//    @Override
//    public void connectInput(AudioComponent input, int index) {
//        throw new UnsupportedOperationException("VFSineWave does not accept direct input.");
//    }
//}



