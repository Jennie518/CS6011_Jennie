package com.example.synthesizer;

import static org.junit.jupiter.api.Assertions.*;

class CableTest {
    SineWave sineWave = new SineWave(440.0);
    VolumeControl volumeControl=  new VolumeControl();
    SpeakerWidget speaker = new SpeakerWidget();

    // Connect them
    Cable cable1 = new Cable((AudioComponent) volumeControl, sineWave); // Connect sineWave to volumeControl
    Cable cable2 = new Cable((AudioComponent) speaker, (AudioComponent) volumeControl);  // Connect volumeControl to speaker
}