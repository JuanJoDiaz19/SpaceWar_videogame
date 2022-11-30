package com.example.tank_battle;

import com.example.tank_battle.control.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class NamesController {

    @FXML
    private Button cancelBTN;

    @FXML
    private Label label1;

    @FXML
    private TextField p1Name;

    @FXML
    private TextField p2Name;
    @FXML
    private TextField p3Name;

    @FXML
    private Button playBTN;


    @FXML
    void cancelA(ActionEvent event) {
        Stage currentStage = (Stage) cancelBTN.getScene().getWindow();
        currentStage.hide();
    }

    @FXML
    void play(ActionEvent event) {
        Stage currentStage = (Stage) cancelBTN.getScene().getWindow();
        Singleton.getInstance().createPlayers(p1Name.getText(), p2Name.getText(),p3Name.getText());
        GameMain.showWindow("gameCanvas.fxml");
        currentStage.hide();
        GameMain.hideWindow("homeMenu.fxml");

    }
}
