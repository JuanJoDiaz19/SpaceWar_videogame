package com.example.tank_battle;

import com.example.tank_battle.control.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class NamesController implements Initializable {

    @FXML
    private Button cancelBTN;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

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
        GameMain.hideWindow("addNames.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFonts();
    }

    InputStream is1 = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    private Font pixeman1 = Font.loadFont(is1, 20);

    private void setFonts(){
        p1Name.setFont(pixeman1);
        p2Name.setFont(pixeman1);
        p3Name.setFont(pixeman1);
        label1.setFont(pixeman1);
        label2.setFont(pixeman1);
        label3.setFont(pixeman1);
    }
}
