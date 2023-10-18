package com.example.synthesizer;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class Cable {
    ArrayList<AudioComponent> audioComponent_;
    protected AnchorPane parent = AudioComponentWidgetBase.parent_;
//    protected Line line = AudioComponentWidgetBase.line_;
//    protected AudioComponentWidgetBase audioComponentWidgetBase = new AudioComponentWidgetBase();

    Cable(AudioComponent speaker, AudioComponent volumeControl) {}
    // fx to connect all inputs audio components
    public void connectInput(AudioComponent input) {
        audioComponent_.add(input);
    }
}
