package com.example.tank_battle;

import com.example.tank_battle.model.Player;
import com.example.tank_battle.model.Scoreboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class GameMain extends Application {

    private static Stage addNames;
    private static Stage homeScreen;
    private static Stage gamePlay;
    private static Stage scores;
    private static Stage winning;

    @Override
    public void start(Stage stage)  {
        showWindow("homeMenu.fxml");
    }
    public static void hideWindow(String fxml){
        if(fxml.equals("homeMenu.fxml")){
            homeScreen.hide();
        } else if (fxml.equals("gameCanvas.fxml")) {
            gamePlay.hide();
        } else if (fxml.equals("addNames.fxml")) {
            addNames.hide();
        }else if (fxml.equals("scores.fxml")) {
            scores.hide();
        }else if (fxml.equals("winning.fxml")) {
            winning.hide();
        }
    }
    public static void showWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource(fxml));
            Parent node = fxmlLoader.load();
            Scene scene = new Scene(node);
            if(fxml.equals("homeMenu.fxml")) {
                homeScreen = new Stage();
                homeScreen.setTitle("SpaceWar");
                homeScreen.setScene(scene);
                homeScreen.show();
            } else if(fxml.equals("gameCanvas.fxml")) {
                gamePlay = new Stage();
                gamePlay.setTitle("SpaceWar");
                gamePlay.setScene(scene);
                gamePlay.show();
            }else if(fxml.equals("scores.fxml")) {
                scores = new Stage();
                scores.setTitle("SpaceWar");
                scores.setScene(scene);
                scores.show();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static void showTransparentWindow(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameMain.class.getResource(fxml));
            Parent node = fxmlLoader.load();
            Scene scene = new Scene(node);
            if(fxml.equals("addNames.fxml")) {
                addNames = new Stage();
                addNames.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                addNames.setX(600);
                addNames.setY(250);
                addNames.setScene(scene);
                addNames.show();
            } else if (fxml.equals("winning.fxml")) {
                winning = new Stage();
                winning.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                winning.setX(600);
                winning.setY(250);
                winning.setScene(scene);
                winning.show();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}