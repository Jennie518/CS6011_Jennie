package com.example.synthesizer;

public class VolumeAdjuster implements AudioComponent {
    private AudioComponent input;
    private double volumeScale; // 音量缩放因子

    public VolumeAdjuster(AudioComponent input, double volumeScale) {
        this.input = input;
        this.volumeScale = volumeScale;
    }

    @Override
    public AudioClip getClip() {
        AudioClip original = input.getClip();
        AudioClip adjustedClip = new AudioClip();

        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            int sample = original.getSample(i);
            sample = (int) (sample * volumeScale);

            sample = Math.min(sample, Short.MAX_VALUE);
            sample = Math.max(sample, Short.MIN_VALUE);

            adjustedClip.setSample(i, sample);
        }
        return adjustedClip;
    }


    @Override
    public boolean hasInput() {
        return true; // VolumeAdjuster 接受输入
    }

    @Override
    public void connectInput(AudioComponent input, int index) {
        this.input = input;
    }
}

