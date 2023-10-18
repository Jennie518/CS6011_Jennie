package com.example.synthesizer;

public class VolumeAdjuster implements AudioComponent {
    private AudioComponent input;
    private double volumeScale; // 音量缩放因子

    VolumeAdjuster(double vol ) {
        volumeScale = vol;
    }


    @Override
    public AudioClip getClip() {
        System.out.println("scale: " + volumeScale);
        if (input == null) {
            throw new IllegalStateException("VolumeAdjuster's input is not connected.");
        }
        AudioClip adjustedClip = new AudioClip();
        AudioClip original = input.getClip();
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
    public void connectInput(AudioComponent input) {
        this.input = input;
    }

    public void setVolumeScale(double volumeScale) {
        this.volumeScale = volumeScale;
    }
    public void removeInput(AudioComponent input) {
        this.input = null;
    }

    public double getVolumeScale() {
        return volumeScale;
    }
}

