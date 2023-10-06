package com.example.synthesizer;

public class Cable {
    private AudioComponent source;
    private AudioComponent destination;

    public Cable(AudioComponent source, AudioComponent destination) {
        this.source = source;
        this.destination = destination;
        connect();

    }
    public void connect() {
        if (destination instanceof Mixer) {
            ((Mixer) destination).addInput(source);
        }

    }
    public void drawConnection() {
        // 这里绘制从源到目的地的直线。您可能需要JavaFX的路径或线条来实现。
    }
}

