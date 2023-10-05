package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import org.w3c.dom.Node;

public abstract class AudioComponentWidgetBase {
    protected Node mainUI; // 主UI组件

    public Node getMainUI() {
        return mainUI;
    }

    public abstract double getValue(); // 获取组件的当前值
    public abstract void setValue(double value); // 设置组件的值
}

