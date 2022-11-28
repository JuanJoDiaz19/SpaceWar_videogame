package com.example.tank_battle;

import com.example.tank_battle.model.Bullet;
import com.example.tank_battle.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Gameplay implements Initializable {
    @FXML
    private HBox hBox;
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private boolean isRunning = true;

    //Players in the game
    private Player player1;
    private Player player2;

    //Bullets in the game
    private ArrayList<Bullet> bulletsPlayer1;
    private ArrayList<Bullet> bulletsPlayer2;

    private Image backgroud;
    
    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        String uriBackgroud = "file:"+ GameMain.class.getResource("gameplayBackground.png").getPath();
        backgroud = new Image(uriBackgroud);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        player1 = new Player("Juancho", canvas, "tank1.png");
        player2 = new Player("Mateo", canvas, "tank2.png");
        draw();
    }

    public void draw() {
        new Thread(
                () -> {
                    while (isRunning) {
                        Platform.runLater(() -> {
                            //gc.setFill();
                           //gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            gc.save();
                            gc.drawImage(backgroud, 0, 0, canvas.getWidth(), canvas.getHeight());
                            gc.restore();
                            player1.draw();
                            player2.draw();
                            /*for (int i = 0; i < enemies.size() ; i++) {
                                enemies.get(i).draw();
                            }*/

                            /*for (int i = 0; i < bullets.size(); i++) {
                                bullets.get(i).draw();
                                if(bullets.get(i).pos.x> canvas.getWidth()+20 ||
                                        bullets.get(i).pos.y > canvas.getHeight()+20 ||
                                        bullets.get(i).pos.y < -20 ||
                                        bullets.get(i).pos.x < -20) {
                                    bullets.remove(i);
                                }
                            }*/
                            //System.out.println(bullets.size());

                            //Colisiones
                            //detectionCollision();

                            doKeyboardActions();

                        });
                        //Sleep
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            Wpressed = false;
        }
        if (keyEvent.getCode() == KeyCode.A) {
            Apressed = false;
        }
        if (keyEvent.getCode() == KeyCode.S) {
            Spressed = false;
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Dpressed = false;
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            Wpressed = true;
        }
        if (keyEvent.getCode() == KeyCode.A) {
            Apressed = true;
        }
        if (keyEvent.getCode() == KeyCode.S) {
            Spressed = true;
        }
        if (keyEvent.getCode() == KeyCode.D) {
            Dpressed = true;
        }
        /*if(keyEvent.getCode() == KeyCode.SPACE) {
            Bullet bullet = new Bullet(canvas, gc, new Vector(avatar.pos.x, avatar.pos.y ), new Vector(2*avatar.direction.x, 2*avatar.direction.y ));
            bullets.add(bullet);
        }*/
    }

    private void doKeyboardActions() {
        if (Wpressed) {
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0)
                player1.moveForward();

        }
        if (Apressed) {
                player1.changeAngle(-6);
        }
        if (Spressed) {
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0)
                player1.moveBackward();
            else
                player1.pos.x-=2;
        }
        if (Dpressed) {

                player1.changeAngle(6);
        }
    }


}
