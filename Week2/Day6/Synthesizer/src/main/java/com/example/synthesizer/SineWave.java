package com.example.synthesizer;

public class SineWave implements AudioComponent {
    private double frequency; // 频率
    private AudioClip clip;   // 生成的音频剪辑

    public SineWave(double frequency) {
        this.frequency = frequency;
//        this.clip = generateSineWaveClip(frequency);
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

    // 生成正弦波音频剪辑
//    private AudioClip generateSineWaveClip(double frequency) {
//        int length = (int) (AudioClip.DURATION * AudioClip.SAMPLE_RATE);
//        byte[] data = new byte[length * 2]; // 16-bit audio, 2 bytes per sample
//        double maxValue = Short.MAX_VALUE;
//
//        for (int i = 0; i < length; i++) {
//            double sampleValue = maxValue * Math.sin(2 * Math.PI * frequency * i / AudioClip.SAMPLE_RATE);
//            short sample = (short) sampleValue;
//            data[2 * i] = (byte) (sample & 0xFF);          // 低 8 位
//            data[2 * i + 1] = (byte) ((sample >> 8) & 0xFF);  // 高 8 位
//        }
//
//        AudioClip sineWaveClip = new AudioClip();
//        System.arraycopy(data, 0, sineWaveClip.getData(), 0, data.length);
//
//        return sineWaveClip;
//    }
//}
