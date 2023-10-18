package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {
    private final ArrayList<AudioComponent> inputs = new ArrayList<>();

    public void addInput(AudioComponent input) {
        inputs.add(input);
    }

    public void removeInput(AudioComponent input) {
        inputs.remove(input);
    }

    @Override
    public AudioClip getClip() {
        // 创建一个新的 AudioClip
        AudioClip mixedClip = new AudioClip();

        // 预先获取所有输入的 AudioClip
        ArrayList<AudioClip> inputClips = new ArrayList<>();
        for (AudioComponent input : inputs) {
            if (input != null) {  // 添加这个检查以避免NullPointerException
                inputClips.add(input.getClip());
            }
        }

        for (int j = 0; j < AudioClip.TOTAL_SAMPLES; j++) {
            int currentSample = mixedClip.getSample(j);
            for (AudioClip clip : inputClips) {
                int inputSample = clip.getSample(j);
                mixedClip.setSample(j, currentSample + inputSample);
                currentSample = mixedClip.getSample(j);  // 更新当前样本值
            }
        }
        return mixedClip;
    }



    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {
        inputs.add(input);
    }
}

