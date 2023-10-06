package com.example.synthesizer;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public abstract class AudioComponentWidgetBase {

    protected Node mainUI; // 主UI组件

    // 默认构造函数，子类可以通过super()调用
    public AudioComponentWidgetBase() {
        mainUI = createMainUI(); // 由子类实现的方法
    }

    // 子类应当提供具体的UI组件
    protected abstract Node createMainUI();

    // 返回主UI，强制转换为VBox
    public VBox getMainUI() {
        return (VBox) mainUI;
    }

    // 获取组件的当前值
    public abstract double getValue();

    // 设置组件的值
    public abstract void setValue(double value);
}
