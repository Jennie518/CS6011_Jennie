package com.example.synthesizer;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SineWaveWidget extends AudioComponentWidgetBase {
    private Slider frequencySlider;
    protected Node createMainUI() {
        VBox vbox = new VBox(10);  // 10是元素之间的间距
        vbox.setPrefSize(120, 90);
        vbox.setStyle("-fx-background-color: lightgray;");
        Label title = new Label("Sine Wave Generator");
        frequencySlider = new Slider(20, 2000, 440); // 示例滑块
        Label frequencyLabel = new Label("Frequency: " + (int) frequencySlider.getValue() + " Hz");

        // 更新标签以显示滑块的当前值
        frequencySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            frequencyLabel.setText("Frequency: " + newValue.intValue() + " Hz");
        });

        Circle inputCircle = new Circle(10, Color.YELLOWGREEN);  // 10 is radius
        Circle outputCircle = new Circle(10, Color.LIGHTPINK); // 10 is radius

        HBox circleBox = new HBox(50);  // 50 is spacing
        circleBox.getChildren().addAll(inputCircle, outputCircle);
        circleBox.setAlignment(Pos.CENTER);


        vbox.getChildren().addAll(title, frequencySlider, frequencyLabel, circleBox);

        return vbox;  // 返回包含标签、滑块和两个圆圈的VBox
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
