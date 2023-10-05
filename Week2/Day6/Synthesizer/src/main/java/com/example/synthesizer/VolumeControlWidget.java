package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import org.w3c.dom.Node;

public class VolumeControlWidget extends AudioComponentWidgetBase {
    private Slider volumeSlider;

    public VolumeControlWidget() {
        super();
        Label title = new Label("Volume Control");
        volumeSlider = new Slider(0, 1, 0.5); // 0 to 1 for volume percentage
        VBox layout = new VBox(title, volumeSlider);
        mainUI = (Node) layout;
    }

    @Override
    public double getValue() {
        return volumeSlider.getValue();
    }

    @Override
    public void setValue(double value) {
        volumeSlider.setValue(value);
    }
}
