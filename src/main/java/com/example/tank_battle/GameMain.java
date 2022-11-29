package com.example.tank_battle;

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
            addNames = new Stage();
            addNames.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            addNames.setX(600);
            addNames.setY(250);
            addNames.setScene(scene);
            addNames.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}