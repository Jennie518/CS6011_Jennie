package com.example.thread;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class answer {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}