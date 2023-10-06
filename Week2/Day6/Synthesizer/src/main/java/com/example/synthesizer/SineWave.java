package com.example.synthesizer;

public class SineWave implements AudioComponent {
    private double frequency; // 频率
    private AudioClip clip;   // 生成的音频剪辑

    public SineWave(double frequency) {
        this.frequency = frequency;
//        this.clip = generateSineWaveClip(frequency);
    }

    public SineWave() {

    }

    @Override
    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++) {
            double time = i / (double) AudioClip.SAMPLE_RATE;
            double value = Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency * time);
            clip.setSample(i, (int) Math.round(value));
        }
        return clip;
    }


    @Override
    public boolean hasInput() {
        return false; // SineWave 不接受输入
    }

    @Override
    public void connectInput(AudioComponent input, int index) {
        assert false; // SineWave 不接受输入，断言失败
    }
}
