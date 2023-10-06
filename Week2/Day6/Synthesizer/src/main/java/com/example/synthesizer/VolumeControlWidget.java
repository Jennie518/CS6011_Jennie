package com.example.synthesizer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class VolumeControlWidget extends AudioComponentWidgetBase {
    private Slider volumeSlider;
    private VBox mainUI;  // Change the type to VBox

    public VolumeControlWidget() {
        Label title = new Label("Volume Control");
        volumeSlider = new Slider(0, 1, 0.5); // 0 to 1 for volume percentage
        mainUI = new VBox(title, volumeSlider);
    }

    @Override
    protected Node createMainUI() {
        Label title = new Label("Volume Control");
        volumeSlider = new Slider(0, 1, 0.5); // 0 to 1 for volume percentage
        return new VBox(title, volumeSlider); // 返回包含标签和滑块的 VBox
    }

    public VBox getMainUI() {
        return mainUI;
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
