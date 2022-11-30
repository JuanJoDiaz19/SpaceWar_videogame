package com.example.tank_battle;

import com.example.tank_battle.model.Pair;
import com.example.tank_battle.model.Player;
import com.example.tank_battle.model.Scoreboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Label name1;

    @FXML
    private Label name10;

    @FXML
    private Label name2;

    @FXML
    private Label name3;

    @FXML
    private Label name4;

    @FXML
    private Label name5;

    @FXML
    private Label name6;

    @FXML
    private Label name7;

    @FXML
    private Label name8;

    @FXML
    private Label name9;

    @FXML
    private Button returnBTN;

    @FXML
    private Label score1;

    @FXML
    private Label score10;

    @FXML
    private Label score2;

    @FXML
    private Label score3;

    @FXML
    private Label score4;

    @FXML
    private Label score5;

    @FXML
    private Label score6;

    @FXML
    private Label score7;

    @FXML
    private Label score8;

    @FXML
    private Label score9;

    @FXML
    private Label scoreBTN;

    @FXML
    private Label topBTN;

    @FXML
    private Label lbBTN;

    @FXML
    private Label nicknameBTN;

    @FXML
    private Label top1;

    @FXML
    private Label top10;

    @FXML
    private Label top2;

    @FXML
    private Label top3;

    @FXML
    private Label top4;

    @FXML
    private Label top5;

    @FXML
    private Label top6;

    @FXML
    private Label top7;

    @FXML
    private Label top8;

    @FXML
    private Label top9;

    InputStream is = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    private Font pixeman70 = Font.loadFont(is, 70.0);
    InputStream is2 = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    private Font pixeman35 = Font.loadFont(is2, 35.0);


    @FXML
    void returnMain(ActionEvent event) {
        GameMain.showWindow("homeMenu.fxml");
        Stage current = (Stage) score9.getScene().getWindow();
        Scoreboard.getInstance().saveInformation();
        current.hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        drawBackground();
        setFonts();
        updateSb();
    }

    private GraphicsContext gc;
    private Image bg;

    public void drawBackground() {

        String uri2 = "file:" + GameMain.class.getResource("background2.png").getPath();
        bg = new Image(uri2);
        gc.save();
        gc.drawImage(bg, 0, 0, 1000, 700);
        gc.restore();

    }

    private void setFonts(){
        lbBTN.setFont(pixeman70);
        score1.setFont(pixeman35);
        score2.setFont(pixeman35);
        score3.setFont(pixeman35);
        score4.setFont(pixeman35);
        score5.setFont(pixeman35);
        score6.setFont(pixeman35);
        score7.setFont(pixeman35);
        score8.setFont(pixeman35);
        score9.setFont(pixeman35);
        score10.setFont(pixeman35);
        name1.setFont(pixeman35);
        name2.setFont(pixeman35);
        name3.setFont(pixeman35);
        name4.setFont(pixeman35);
        name5.setFont(pixeman35);
        name6.setFont(pixeman35);
        name7.setFont(pixeman35);
        name8.setFont(pixeman35);
        name9.setFont(pixeman35);
        name10.setFont(pixeman35);
        top1.setFont(pixeman35);
        top2.setFont(pixeman35);
        top3.setFont(pixeman35);
        top4.setFont(pixeman35);
        top5.setFont(pixeman35);
        top6.setFont(pixeman35);
        top7.setFont(pixeman35);
        top8.setFont(pixeman35);
        top9.setFont(pixeman35);
        top10.setFont(pixeman35);

    }

    public void updateSb(){
        ArrayList<Player> players = Scoreboard.getInstance().getPlayers();
        if(players!=null) {
            if (players.size() >= 1 && players.get(0) != null) {
                name1.setText(players.get(0).getName());
                score1.setText("won: " + players.get(0).getGamesWon() + "");
            }
            if (players.size() >= 2 && players.get(1) != null) {
                name2.setText(players.get(1).getName());
                score2.setText("won: " + players.get(1).getGamesWon() + "");
            }
            if (players.size() >= 3 && players.get(2) != null) {
                name3.setText(players.get(2).getName());
                score3.setText("won: " + players.get(2).getGamesWon() + "");
            }
            if (players.size() >= 4 && players.get(3) != null) {
                name4.setText(players.get(3).getName());
                score4.setText("won: " + players.get(3).getGamesWon() + "");
            }
            if (players.size() >= 5 && players.get(4) != null) {
                name5.setText(players.get(4).getName());
                score5.setText("won: " + players.get(4).getGamesWon() + "");
            }
            if (players.size() >= 6 && players.get(5) != null) {
                name6.setText(players.get(5).getName());
                score6.setText("won: " + players.get(5).getGamesWon() + "");
            }
            if (players.size() >= 7 && players.get(6) != null) {
                name7.setText(players.get(6).getName());
                score7.setText("won: " + players.get(6).getGamesWon() + "");
            }
            if (players.size() >= 8 && players.get(7) != null) {
                name8.setText(players.get(7).getName());
                score8.setText("won: " + players.get(7).getGamesWon() + "");
            }
            if (players.size() >= 9 && players.get(8) != null) {
                name9.setText(players.get(8).getName());
                score9.setText("won: " + players.get(8).getGamesWon() + "");
            }
            if (players.size() >= 10 && players.get(9) != null) {
                name10.setText(players.get(9).getName());
                score10.setText("won: " + players.get(9).getGamesWon() + "");
            }
        }

    }
}
