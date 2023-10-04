package com.example.synthesizer;

public class Mixer implements AudioComponent {
        private final AudioComponent[] inputs; // 用于存储多个输入音频组件的数组

        public Mixer(int numInputs) {
            inputs = new AudioComponent[numInputs]; // 初始化数组以容纳指定数量的输入
        }

        @Override
        public AudioClip getClip() {
            // 假设输入数组中的所有音频剪辑具有相同的长度
            int length = inputs[0].getClip().getData().length;
            byte[] mixedData = new byte[length];

            // 遍历输入音频组件，将它们的音频数据相加
            for (AudioComponent input : inputs) {
                byte[] inputData = input.getClip().getData();

                for (int j = 0; j < length; j++) {
                    // 将每个输入音频的对应位置的采样值相加
                    mixedData[j] += inputData[j];
                }
            }

            // 创建一个新的 AudioClip 来包装混合后的音频数据
            AudioClip mixedClip = new AudioClip();
            System.arraycopy(mixedData, 0, mixedClip.getData(), 0, length);

            return mixedClip;
        }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input, int index) {
            if (index >= 0 && index < inputs.length) {
                inputs[index] = input; // 将输入音频组件存储在指定的索引位置
            } else {
                throw new IllegalArgumentException("Invalid input index");
            }
        }

        // 其他必要的方法和逻辑
    }

