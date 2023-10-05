package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import org.w3c.dom.Node;

public class SineWaveWidget extends AudioComponentWidgetBase {
    private Slider frequencySlider;

    public SineWaveWidget() {
        super();
        Label title = new Label("Sine Wave Generator");
        frequencySlider = new Slider(20, 2000, 440);
        VBox layout = new VBox(title, frequencySlider);
        mainUI = (Node) layout;
    }

    @Override
    public double getValue() {
        return frequencySlider.getValue();
    }

    @Override
    public void setValue(double value) {
        frequencySlider.setValue(value);
    }
}
