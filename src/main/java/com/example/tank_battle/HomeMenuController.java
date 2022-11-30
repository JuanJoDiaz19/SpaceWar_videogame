package com.example.tank_battle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeMenuController {

    @FXML
    private Button playButton;

    @FXML
    private ImageView scoresButton;

    @FXML
    void play(ActionEvent event) {
        GameMain.showTransparentWindow("addNames.fxml");
        Stage currentStage = (Stage) playButton.getScene().getWindow();
        //currentStage.hide();
    }

    @FXML
    void scores(ActionEvent event) {
        //System.out.println("RICO SEMEN");
        GameMain.showWindow("scores.fxml");
        Stage currentStage = (Stage) playButton.getScene().getWindow();
        currentStage.hide();
    }

}
