package com.example.synthesizer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    //member variables
    public static AnchorPane mainCanvas_;
    public static ArrayList<AudioComponentWidgetBase> widgets_ = new ArrayList<>();

    ArrayList<AudioComponentWidgetBase> SpeakerWidgets = SpeakerWidget.SpeakerWidgets_;

    @Override
    public void start(Stage stage) {
        // Parent
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 500);
        /*********************** Top panel for the scene ***********************/
        HBox topPanel = new HBox();
        topPanel.setPadding(new Insets(5));
        topPanel.setSpacing(80);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setStyle("-fx-background-color: oldlace");

        Button E4_Note = new Button("E4");
        Button B_Note = new Button("B3");
        Button G_Note = new Button("G3");
        Button D_Note = new Button("D3");
        Button A_Note = new Button("A2");
        Button E2_Note = new Button("E2");

        // Reference: https://en.wikipedia.org/wiki/Guitar_tunings
        E4_Note.setOnAction(e -> PlayNoteKey(329.63));
        B_Note.setOnAction(e -> PlayNoteKey(246.94));
        G_Note.setOnAction(e -> PlayNoteKey(196.00));
        D_Note.setOnAction(e -> PlayNoteKey(146.83));
        A_Note.setOnAction(e -> PlayNoteKey(110.00));
        E2_Note.setOnAction(e -> PlayNoteKey(82.41));

        topPanel.getChildren().add(E4_Note);
        topPanel.getChildren().add(B_Note);
        topPanel.getChildren().add(G_Note);
        topPanel.getChildren().add(D_Note);
        topPanel.getChildren().add(A_Note);
        topPanel.getChildren().add(E2_Note);


        /*********************** right panel for the scene ***********************/
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(5));
        rightPanel.setSpacing(80);
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setStyle("-fx-background-color: oldlace");

        Button sineWaveButton = new Button("SineWave");
        sineWaveButton.setTextFill(Color.BLACK);
        sineWaveButton.setOnAction(e -> createSineWaveComponent());


        Button volumeButton = new Button("Volume");
        volumeButton.setTextFill(Color.BLACK);
        volumeButton.setOnAction(e -> createVolumeComponent());


        // add children of right panel
        rightPanel.getChildren().add(sineWaveButton);
        rightPanel.getChildren().add(volumeButton);


        /*********************** center panel***********************/
        mainCanvas_ = new AnchorPane();
        mainCanvas_.setStyle("-fx-background-color: antiquewhite");


        // create a speaker widget on the mainCanvas
        SpeakerWidget speakerWidget = new SpeakerWidget();
        speakerWidget.CreateSpeakerWidget();


        /*********************** bottom panel***********************/
        HBox buttomPanel = new HBox();
        buttomPanel.setAlignment(Pos.BOTTOM_CENTER);
        buttomPanel.setStyle("-fx-background-color: oldlace");
        buttomPanel.setPadding(new Insets(10));
        Button playBtn = new Button("PLAY");
        playBtn.setOnAction(e -> PlayNetwork());
        buttomPanel.getChildren().add(playBtn);

        //*********************** Put all panels into the parent ***********************//
        // put stuffs into the root container
        root.setRight(rightPanel);
        root.setTop(topPanel);
        root.setBottom(buttomPanel);
        root.setCenter(mainCanvas_);

        stage.setTitle("My Synthesizer");
        stage.setScene(scene);
        stage.show();
    }


    private void createVolumeComponent() {
        AudioComponent SineWave = new SineWave(440);
        VolumeAdjuster vol = new VolumeAdjuster(SineWave,1);
        VolumeWidget vW = new VolumeWidget(vol, mainCanvas_, "Volume");
        vW.CreateVolumeWidget();
        vW.setLayoutX(30);
        vW.setLayoutY(350);
        widgets_.add(vW);
        System.out.println("Volume widget created");
    }

    private void createSineWaveComponent() {
        AudioComponent SineWave = new SineWave(440);
        SineWaveWidget sineWidget = new SineWaveWidget(SineWave, mainCanvas_, "SineWave");
        sineWidget.CreateSineWaveWidget();
        widgets_.add(sineWidget); // keep tack of all widgets
        double layoutX_ = 20;
        sineWidget.setLayoutX(layoutX_);
        double layoutY_ = 40;
        sineWidget.setLayoutY(layoutY_);
        System.out.println("SineWave widget created");
    }

    private void PlayNetwork() {
        if (SpeakerWidgets.isEmpty()) {
            System.out.println("widget size is equal to 0");
            return;
        }
        try {
            Clip c = AudioSystem.getClip();
            Mixer mixer = new Mixer();

            for (AudioComponentWidgetBase speakerWidget : SpeakerWidget.SpeakerWidgets_) {
                mixer.connectInput(speakerWidget.getAudioComponent());
            }

            System.out.println("widgets_ is " + widgets_.size());
            System.out.println("Speakerwidgets_ is " + SpeakerWidgets.size());

            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
            byte[] data = mixer.getClip().getData();
            c.open(format, data, 0, data.length);
            c.start();
            c.addLineListener(e -> handleAudioDone(e, c));
            // its job is to wait until the event is stopped and then close the clip

        } catch (LineUnavailableException e) {
            System.out.println("failed to open the clip");
        }
    }

    private void PlayNoteKey(double frequency) {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format = new AudioFormat(44100, 16, 1, true, false);

            AudioComponent KeyNote = new SineWave((int) frequency);
            AudioClip clip = KeyNote.getClip();

            c.open(format, clip.getData(), 0, clip.getData().length);
            c.start();
            c.addLineListener(e -> handleAudioDone(e, c));
            // its job is to wait until the event is stopped and then close the clip

        } catch (LineUnavailableException e) {
            System.out.println("failed to open the clip");
        }

    }

    private void handleAudioDone(LineEvent e, Clip c) {
        if (e.getType() == LineEvent.Type.STOP) {
            System.out.println("Close clip");
            c.close();
        }
    }

    public static void main(String[] args) {
                launch();
//        Application.launch(SynthesizeApplication.class); // this will run my JavaFx GUI app, basically it will run the start()
    }


}
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import javax.sound.sampled.*;
//
//public class SynthesizeApplication extends Application {
//    private VolumeControlWidget volumeControl;
//    private SineWaveWidget sineWaveControl;
//    private AnchorPane mainCenter;
//
//    @Override
//    public void start(Stage stage) {
//        BorderPane root = setupUI();
//
//        Scene scene = new Scene(root, 800, 600);
//        stage.setTitle("Synthesizer");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    private BorderPane setupUI() {
//        VBox rightPanel = setupRightPanel();
//        mainCenter = new AnchorPane();
//        mainCenter.setStyle("-fx-background-color: #f1e6be");
//        BorderPane root = new BorderPane();
//        root.setCenter(mainCenter);
//        root.setRight(rightPanel);
//        return root;
//    }
//
//    private Button showButton() {
//        Button showBotton = new Button("Show frequency");
//        showBotton.setOnAction(event -> {
//            if (!mainCenter.getChildren().contains(sineWaveControl.getMainUI())) {
//                AnchorPane.setTopAnchor(sineWaveControl.getMainUI(), 50.0);
//                AnchorPane.setLeftAnchor(sineWaveControl.getMainUI(), 50.0);
//                mainCenter.getChildren().add(sineWaveControl.getMainUI());
//            }
//        });
//        return showBotton;
//    }
//    private Button playButton() {
//        Button playButton = new Button("Play");
//        playButton.setOnAction(event -> {
//            double frequency = sineWaveControl.getValue();
//            playSound(frequency);
//        });
//        return playButton;
//    }
//
//    private VBox setupRightPanel() {
//        volumeControl = new VolumeControlWidget();
//        sineWaveControl = new SineWaveWidget();
//
//        Button showBotton = showButton();
//package com.example.synthesizer;
//        Button playBotton = playButton();
//        VBox rightPanel = new VBox(volumeControl.getMainUI(),showBotton,playBotton);
//        rightPanel.setSpacing(15);
//        rightPanel.setStyle("-fx-background-color: lightblue");
//        rightPanel.setPadding(new Insets(20));
//        rightPanel.setPrefWidth(150);
//        return rightPanel;
//    }
//
//    private void playSound(double frequency) {
//        // 获取音量值
//        double volumeValue = volumeControl.getValue();
//        try {
//            Clip c = AudioSystem.getClip();
//            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);
//
//            AudioComponent sineWave = new SineWave(frequency);
//            AudioComponent quieterSineWave = new VolumeAdjuster(sineWave, volumeValue);
//            AudioClip mixedAudio = quieterSineWave.getClip();
//            c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);
//
//            c.addLineListener(new AudioListener(c));
//
//            System.out.println("About to play...");
//            c.start();
//            c.loop(2);
//
//            // 由于您已经使用了LineListener，因此不再需要while循环来检查音频是否完成播放
//
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
