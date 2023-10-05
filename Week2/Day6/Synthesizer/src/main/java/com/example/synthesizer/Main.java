package com.example.synthesizer;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            // 创建第一个正弦波和 Volume Adjuster
            AudioComponent sineWave1 = new SineWave(440);
            AudioComponent quieterSineWave1 = new VolumeAdjuster(sineWave1, 0.4);

            // 创建第二个正弦波和 Volume Adjuster
            AudioComponent sineWave2 = new SineWave(220);
            AudioComponent quieterSineWave2 = new VolumeAdjuster(sineWave2, 0.2);

            // 创建 Mixer，并将两个调整过音量的正弦波连接到 Mixer
            Mixer mixer = new Mixer(2);
            mixer.connectInput(quieterSineWave1, 0);
            mixer.connectInput(quieterSineWave2, 1);

//            // 获取混合后的音频剪辑
            AudioClip mixedAudio = mixer.getClip();
            c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);
            // 创建 LinearRamp 对象，设置起始值和结束值
            LinearRamp linearRamp = new LinearRamp(50, 2000);

            // 创建 VFSineWave 对象，将 LinearRamp 作为输入
            VFSineWave vfSineWave = new VFSineWave(1.0f, 44100.0f, linearRamp);

            // 获取 VFSineWave 的音频剪辑
            AudioClip vfSineWaveClip = vfSineWave.getClip();
//            c.open(format16, vfSineWaveClip.getData(), 0, vfSineWaveClip.getData().length);
            System.out.println("About to play...");
            c.start();
            c.loop(2);

            while (c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning()) {
                // 等待音频播放完成
            }
            System.out.println("Done.");
            c.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
