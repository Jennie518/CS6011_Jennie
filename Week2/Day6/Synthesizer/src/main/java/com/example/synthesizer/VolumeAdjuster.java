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
        AudioClip original = input.getClip(); // 获取输入的音频剪辑

        // 创建一个新的音频剪辑，用于存储调整后的音频数据
        AudioClip adjustedClip = new AudioClip();

        // 获取原始音频数据
        byte[] originalData = original.getData();
        int length = originalData.length;

        // 创建一个新的字节数组来存储调整后的音频数据
        byte[] adjustedData = new byte[length];

        // 调整音频数据的音量
        for (int i = 0; i < length; i++) {
            int sample = originalData[i] & 0xFF; // 将字节转换为无符号整数
            sample = (int) (sample * volumeScale); // 调整音量
            // 将调整后的样本值存储回字节数组
            adjustedData[i] = (byte) (sample & 0xFF);
        }

        // 将调整后的音频数据设置到 adjustedClip 中
        adjustedClip.setData(adjustedData);

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

