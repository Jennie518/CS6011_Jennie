package com.example.synthesizer;

import org.junit.jupiter.api.Test;
class VolumeAdjusterTest {
    public void runAllTest() {
        SineWave sineWave = new SineWave( 44100);
        VolumeAdjuster MyVolume = new VolumeAdjuster(0.5);
        MyVolume.connectInput(sineWave);
    }

}