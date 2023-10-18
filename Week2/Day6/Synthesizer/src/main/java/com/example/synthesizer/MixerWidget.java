package com.example.synthesizer;

import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MixerWidget extends AudioComponentWidgetBase {

    private Circle mixerInputConnector_;
    private Circle mixerOutputConnector_;
    private static final double MIXER_RADIUS_ = 20;

    private ArrayList<AudioComponent> inputComponents_ = new ArrayList<>();
    public static ArrayList<AudioComponentWidgetBase> MixerWidgets_ = new ArrayList<>();

    public MixerWidget(Mixer ac, AnchorPane parent, String name) {
        super(ac, parent, name); // Assuming Mixer doesn't have its own AudioComponent for now
    }

    public void createMixerWidget() {
        // UI setup for Mixer Widget
        VBox mixerBox = new VBox();
        mixerBox.setAlignment(Pos.CENTER);
        mixerInputConnector_ = new Circle(MIXER_RADIUS_, Color.BLUE);
        mixerInputConnector_.setOnMousePressed(e -> startConnection(e, mixerInputConnector_));
        mixerInputConnector_.setOnMouseDragged(e -> moveConnection(e, mixerInputConnector_));
        mixerInputConnector_.setOnMouseReleased(e -> endConnection(e, mixerInputConnector_));

        mixerOutputConnector_ = new Circle(MIXER_RADIUS_, Color.GREEN);
        mixerOutputConnector_.setOnMousePressed(e -> startConnection(e, mixerOutputConnector_));
        mixerOutputConnector_.setOnMouseDragged(e -> moveConnection(e, mixerOutputConnector_));
        mixerOutputConnector_.setOnMouseReleased(e -> endConnection(e, mixerOutputConnector_));

        mixerBox.getChildren().add(mixerInputConnector_);
        mixerBox.getChildren().add(mixerOutputConnector_);

        centerComponent_.getChildren().add(mixerBox);

    }

    @Override
    public void handleDrag(MouseEvent e) {
        // Add drag handling for the mixer widget
    }

    @Override
    public void startDrag(MouseEvent e) {
        // Add start drag handling for the mixer widget
    }

    @Override
    public void closeWidget() {
        // Add close handling for the mixer widget
    }

    // Assuming Mixer receives multiple inputs but gives one output.
    public void connectInput(AudioComponent inputComponent) {
        inputComponents_.add(inputComponent);
    }

    public void disconnectInput(AudioComponent inputComponent) {
        inputComponents_.remove(inputComponent);
    }

    public AudioComponent getMixerOutput() {
        // Combine the audio from all inputComponents_ and return it.
        // This logic depends on the specifics of your AudioComponent implementation.
        return null; // Placeholder. Replace this with actual mixing logic.
    }
}
