package com.example.synthesizer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SineWaveWidget extends AudioComponentWidgetBase {

    Slider slider = new Slider(220, 880, 440);
    Label title = new Label();
    public static  Circle OutputCircle_;

    public SineWaveWidget (AudioComponent ac, AnchorPane parent, String name) {
        super(ac, parent, name);
    }

    public void CreateSineWaveWidget( ){

        title.setMouseTransparent(true);
        title.setText("SineWave 440Hz");
        slider.setOnMouseDragged(e-> handleSlider(e));
        slider.setPadding(new Insets(6));
        centerComponent_.getChildren().add(title);
        centerComponent_.getChildren().add(slider);
        baseLayout_.setPrefSize(200,60);

        VBox volumebar = new VBox();
        volumebar.setAlignment(Pos.CENTER);

        OutputCircle_ = new Circle(10);
        OutputCircle_.setFill(Color.GREEN);

        OutputCircle_.setOnMousePressed(e -> startConnection(e, OutputCircle_));
        OutputCircle_.setOnMouseDragged(e -> moveConnection(e, OutputCircle_));
        OutputCircle_.setOnMouseReleased(e -> endConnection(e, OutputCircle_));

        rightRightSide_.getChildren().add(OutputCircle_);

    }

    public void handleSlider(MouseEvent e) {
        int frequency = (int) slider.getValue();
        title.setText("SineWave " + frequency + "Hz");
        SineWave sw = (SineWave) audioComponent_;
        sw.setFrequency(frequency);
//        audioComponent_ = new SineWave(frequency);
    }

    @Override
    public void handleDrag(MouseEvent e) {
        double mouseDelX = e.getSceneX() - mouseStartDragX_;
        double mouseDelY = e.getSceneY() - mouseStartDragY_;
        this.relocate(widgetStartDragX_ + mouseDelX, widgetStartDragY_ + mouseDelY);

        Bounds parentBounds = parent_.getBoundsInParent();
        Bounds bounds = SineWaveWidget.OutputCircle_.localToScene(SineWaveWidget.OutputCircle_.getBoundsInLocal());

        if( line_ != null ) {
            line_.setStartX(bounds.getCenterX() - parentBounds.getMinX());
            line_.setStartY(bounds.getCenterY() - parentBounds.getMinY());
            System.out.println("dragging the widget and the line");
        }
    }
}