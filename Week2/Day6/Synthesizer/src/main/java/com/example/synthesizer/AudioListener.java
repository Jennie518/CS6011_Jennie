package com.example.synthesizer;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
public class AudioListener implements LineListener {
    private Clip clip_;

    public AudioListener(Clip clip) {
        this.clip_ = clip;
    }

    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            System.out.println("Audio playback completed!");
            clip_.close();
        }
    }
}
