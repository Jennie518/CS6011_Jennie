package com.example.synthesizer;

public class SpeakerWidget {
    private AudioComponent input;

    public void setInput(AudioComponent input) {
        this.input = input;
    }

    public AudioComponent getInput() {
        return this.input;
    }

    public void playSound() {
        if(input != null) {
            // 从输入获取声音并播放
            // ...
        }
    }
}
