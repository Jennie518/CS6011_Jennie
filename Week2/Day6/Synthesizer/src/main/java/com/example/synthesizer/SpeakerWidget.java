package com.example.synthesizer;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class SpeakerWidget {
    //fields
    public final Mixer mixer_;
    public static Circle speakerCircle_;
    public static double SPEAKER_RADIUS = 20;
    public static ArrayList<AudioComponentWidgetBase> SpeakerWidgets_ = new ArrayList<>();
//    public static ArrayList<AudioComponent> SpeakerAudioComponents_ = new ArrayList<>(); // maybe I might need it later

    public SpeakerWidget () {
        mixer_ = new Mixer();
        speakerCircle_ = new Circle(650, 400, SPEAKER_RADIUS, Color.BLACK);
        AnchorPane mainWindow =  SynthesizeApplication.mainCanvas_;
        mainWindow.getChildren().add(speakerCircle_);
    }

    public void connectInput(AudioComponentWidgetBase input) {
        SpeakerWidgets_.add(input);
        mixer_.connectInput(input.getAudioComponent());
    }
}