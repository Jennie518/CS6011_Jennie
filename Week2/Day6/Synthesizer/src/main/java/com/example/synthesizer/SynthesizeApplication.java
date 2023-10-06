package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.sound.sampled.*;

public class SynthesizeApplication extends Application {
    private VolumeControlWidget volumeControl;
    private SineWaveWidget sineWaveControl;
    private AnchorPane mainCenter;

    @Override
    public void start(Stage stage) {
        BorderPane root = setupUI();

        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane setupUI() {
        VBox rightPanel = setupRightPanel();
        mainCenter = new AnchorPane();
        mainCenter.setStyle("-fx-background-color: #f1e6be");
        BorderPane root = new BorderPane();
        root.setCenter(mainCenter);
        root.setRight(rightPanel);
        return root;
    }

    private Button showButton() {
        Button showBotton = new Button("Show frequency");
        showBotton.setOnAction(event -> {
            if (!mainCenter.getChildren().contains(sineWaveControl.getMainUI())) {
                AnchorPane.setTopAnchor(sineWaveControl.getMainUI(), 50.0);
                AnchorPane.setLeftAnchor(sineWaveControl.getMainUI(), 50.0);
                mainCenter.getChildren().add(sineWaveControl.getMainUI());
            }
        });
        return showBotton;
    }
    private Button playButton() {
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            double frequency = sineWaveControl.getValue();
            playSound(frequency);
        });
        return playButton;
    }

    private VBox setupRightPanel() {
        volumeControl = new VolumeControlWidget();
        sineWaveControl = new SineWaveWidget();

        Button showBotton = showButton();
        Button playBotton = playButton();
        VBox rightPanel = new VBox(volumeControl.getMainUI(),showBotton,playBotton);
        rightPanel.setSpacing(15);
        rightPanel.setStyle("-fx-background-color: lightblue");
        rightPanel.setPadding(new Insets(20));
        rightPanel.setPrefWidth(150);
        return rightPanel;
    }

    private void playSound(double frequency) {
        // 获取音量值
        double volumeValue = volumeControl.getValue();
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            AudioComponent sineWave = new SineWave(frequency);
            AudioComponent quieterSineWave = new VolumeAdjuster(sineWave, volumeValue);
            AudioClip mixedAudio = quieterSineWave.getClip();
            c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);

            c.addLineListener(new AudioListener(c));

            System.out.println("About to play...");
            c.start();
            c.loop(2);

            // 由于您已经使用了LineListener，因此不再需要while循环来检查音频是否完成播放

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
