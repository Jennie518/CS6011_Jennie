package com.example.synthesizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.sound.sampled.*;

import java.io.IOException;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Button playButton = new Button("Play");

        // Frequency Slider
        Slider frequencySlider = new Slider(20, 2000, 440); // default value is 440Hz
        Label frequencyLabel = new Label("Frequency: ");

        frequencySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // You can update the frequency here if needed
            // sineWave.setFrequency(newValue.doubleValue());
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Sine Wave Generator"));
        borderPane.setCenter(playButton);
        HBox frequencyBox = new HBox(frequencyLabel, frequencySlider);
        borderPane.setBottom(frequencyBox);

        playButton.setOnAction(event -> {
            // When playing sound, get the frequency from the slider
            double frequency = frequencySlider.getValue();
            playSound(frequency);
        });

        Scene scene = new Scene(borderPane, 100, 150);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

        private void playSound(double frequency) {
            // Create sine wave generator with given frequency
            // Rest of your sound playing logic...
            try {
                Clip c = AudioSystem.getClip();
                AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

                AudioComponent sineWave = new SineWave(frequency);
                AudioComponent quieterSineWave = new VolumeAdjuster(sineWave, 0.4);

//            // 获取混合后的音频剪辑
                AudioClip mixedAudio = quieterSineWave.getClip();
                c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);
                // 创建 LinearRamp 对象，设置起始值和结束值
                LinearRamp linearRamp = new LinearRamp(50, 2000);

                // 创建 VFSineWave 对象，将 LinearRamp 作为输入
                VFSineWave vfSineWave = new VFSineWave(1.0f, 44100.0f, linearRamp);

                // 获取 VFSineWave 的音频剪辑
                AudioClip vfSineWaveClip = vfSineWave.getClip();
//            c.open(format16, vfSineWaveClip.getData(), 0, vfSineWaveClip.getData().length);
                System.out.println("About to play...");
                c.start();
                c.loop(2);

                while (c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning()) {
                    // 等待音频播放完成
                }
                System.out.println("Done.");
                c.close();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        launch();
    }
}