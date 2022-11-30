package com.example.tank_battle;

import com.example.tank_battle.control.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class WinningController implements Initializable {

    @FXML
    private Button cancelBTN;

    @FXML
    private Label labelResul2;

    @FXML
    private Label labelResult1;
//carenalga
    @FXML
    private Button playBTN;

    InputStream is1 = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    InputStream is2 = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    private Font pixeman1 = Font.loadFont(is1, 47.0);
    private Font pixeman2 = Font.loadFont(is2, 45.0);

    @FXML
    void cancelA(ActionEvent event) {
        GameMain.showWindow("scores.fxml");
        GameMain.hideWindow("winning.fxml");
        GameMain.hideWindow("gameCanvas.fxml");
        Singleton.getInstance().stopSound();

    }

    @FXML
    void play(ActionEvent event) {
        GameMain.showTransparentWindow("addNames.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFonts();
        labelResul2.setText(Singleton.getInstance().winningTeam);
    }
    private void setFonts(){
        labelResult1.setFont(pixeman1);
        labelResul2.setFont(pixeman2);
    }
}
