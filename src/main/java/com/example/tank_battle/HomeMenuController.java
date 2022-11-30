package com.example.tank_battle;

import com.example.tank_battle.control.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeMenuController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/IntroSong.wav");
        Singleton.getInstance().playSound();
    }
}
