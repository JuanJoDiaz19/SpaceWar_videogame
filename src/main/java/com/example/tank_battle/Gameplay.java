package com.example.tank_battle;

import com.example.tank_battle.model.Bullet;
import com.example.tank_battle.model.Obstacle;
import com.example.tank_battle.model.Player;
import com.example.tank_battle.model.Vector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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
    private Obstacle[][] obstaclesMap;
    private ArrayList<Obstacle> obstacles;
    
    //Estados de las teclas para el jugador 1
    private boolean Wpressed = false;
    private boolean Apressed = false;
    private boolean Spressed = false;
    private boolean Dpressed = false;

    //Estados de las teclas para el jugador 2
    private boolean upPressed =  false;
    private boolean downPressed = false;
    private boolean rightPressed = false;
    private boolean leftPressed = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        String uriBackgroud = "file:"+ GameMain.class.getResource("gameplayBackground.png").getPath();
        backgroud = new Image(uriBackgroud);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        //Initializing the bullets in the game

        bulletsPlayer1 = new ArrayList<>();
        bulletsPlayer2 = new ArrayList<>();
        obstacles = new ArrayList<>();
        walletsp= new ArrayList<>();

        //Initializing the matrix
        Integer obstaclesInMap[][] = new Integer[][]{{null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
                {null, 1, null,null, 2, null, null, null,null,null,null,null,null,null,null,  1, null, null,  2, null },
                {null, 2, null,null, 1, null, null, 1,2,1,2,1,2,null,null, 2, null, null,  1, null },
                {null, 1, null,null,2, null, null, null,null,null,null,null,null,null,null, 1, null, null,2, null },
                {null, null, 2,1, null,  null,  2, null,  2, null, null,  2, null, 2, null, null,  2, 1, null, null},
                {null, null,1, 2, null,  null, 1, 2, 1, null, null, 1,2 , 1, null, null, 1, 2, null, null},
                {null, null,2, 1, null,  null,  2, null, 2, null, null,  2, null, 2, null, null,  2, 1, null, null},
                {null, 1, null,null, 2, null, null, null,null,null,null,null,null,null,null,  1, null, null,  2, null },
                {null, 2, null,null, 1, null, null, 2, 1 ,2 ,1 ,2 ,1 ,null,null, 2, null, null,  1, null },
                {null, 1, null,null, 2, null, null, null,null,null,null,null,null,null,null, 1, null, null,  2, null },
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}};

        obstaclesMap = new Obstacle[12][20];
        for (int i = 0; i < obstaclesMap.length ; i++) {
            for (int j = 0; j < obstaclesMap[0].length ; j++) {
                if(obstaclesInMap[i][j] == null){
                    obstaclesMap[i][j] = null;
                } else if(obstaclesInMap[i][j] == 1) {

                    obstaclesMap[i][j] = new Obstacle(canvas, gc, i, j, 1);
                    obstacles.add(obstaclesMap[i][j]);
                    walletsp.add(new Pair<>(obstaclesMap[i][j].getX(), obstaclesMap[i][j].getY()));
                } else {
                    obstaclesMap[i][j] = new Obstacle(canvas, gc, i, j, 2);
                    obstacles.add(obstaclesMap[i][j]);
                    walletsp.add(new Pair<>(obstaclesMap[i][j].getX(), obstaclesMap[i][j].getY()));
                }
            }
        }
        player1 = new Player("Juancho", canvas, "tank1.png");
        player2 = new Player("Mateo", canvas, "tank2.png");
        draw();
    }

    public void draw() {
        new Thread(
                () -> {
                    while (isRunning) {
                        Platform.runLater(() -> {
                            gc.save();
                            gc.drawImage(backgroud, 0, 0, canvas.getWidth(), canvas.getHeight());
                            gc.restore();
                            player1.draw();
                            player2.draw();

                            for (int i = 0; i < obstaclesMap.length; i++) {
                                for (int j = 0; j < obstaclesMap[0].length ; j++) {
                                    if (obstaclesMap[i][j] != null) {
                                        obstaclesMap[i][j].draw();
                                    }
                                }
                            }
                            for (int i = 0; i < bulletsPlayer1.size() ; i++) {
                                bulletsPlayer1.get(i).draw();
                            }
                            for (int i = 0; i < bulletsPlayer2.size() ; i++) {
                                bulletsPlayer2.get(i).draw();
                            }
                            //Colisiones
                            //detectionCollision();
                            doKeyboardActions();
                        });
                        //Sleep (Mimiendo)
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
        if (keyEvent.getCode() == KeyCode.UP){
            upPressed = false;
        }
        if (keyEvent.getCode() == KeyCode.DOWN){
            downPressed = false;
        }
        if (keyEvent.getCode() == KeyCode.RIGHT){
            rightPressed = false;
        }
        if (keyEvent.getCode() == KeyCode.LEFT){
            leftPressed = false;
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        //System.out.println(keyEvent.getCode());
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
        if (keyEvent.getCode() == KeyCode.UP){
            upPressed = true;
        }
        if (keyEvent.getCode() == KeyCode.DOWN){
            downPressed = true;
        }
        if (keyEvent.getCode() == KeyCode.RIGHT){
            rightPressed = true;
        }
        if (keyEvent.getCode() == KeyCode.LEFT){
            leftPressed = true;
        }
        if(keyEvent.getCode() == KeyCode.SPACE && player1.numBullets > 0 ) {
            Bullet bullet = new Bullet(canvas, gc, new Vector(player1.pos.x, player1.pos.y ), new Vector(2.5*player1.direction.x, 2.5*player1.direction.y ), 1);
            bulletsPlayer1.add(bullet);
        }
        if(keyEvent.getCode() == KeyCode.SHIFT && player2.numBullets > 0) {
            Bullet bullet = new Bullet(canvas, gc, new Vector(player2.pos.x, player2.pos.y ), new Vector(2.5*player2.direction.x, 2.5*player2.direction.y ), 2);
            bulletsPlayer2.add(bullet);
            player2.numBullets--;
        }
    }

    private void doKeyboardActions() {
        if (Wpressed) {
            boolean flag= true;
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0 && player1.pos.y<=canvas.getHeight() && player1.pos.y>0)
                for(int i=0; i< walletsp.size(); i++){

                }
                if(flag) player1.moveForward();

            if(player1.pos.x>canvas.getWidth()){
                player1.pos.x=15;
            }
            if(player1.pos.x<=0){
                player1.pos.x=canvas.getWidth();
            }
            if(player1.pos.y>canvas.getHeight()){
                player1.pos.y=canvas.getHeight();
            }
            if(player1.pos.y<=0) player1.pos.y=5;
        }
        if (Apressed) {
            player1.changeAngle(-3);
        }
        if (Spressed) {
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0 && player1.pos.y<=canvas.getHeight() && player1.pos.y>0)
                player1.moveBackward();
            if(player1.pos.x>canvas.getWidth()){
                player1.pos.x=15;
            }
            if(player1.pos.x<=0){
                player1.pos.x=canvas.getWidth();
            }
            if(player1.pos.y>canvas.getHeight()){
                player1.pos.y=canvas.getHeight();
            }
            if(player1.pos.y<=0) {
                player1.pos.y=5;
            }
        }
        if (Dpressed) {
            player1.changeAngle(3);
        }
        //Codigo pora el player 2
        if (upPressed) {
            if(player2.pos.x<=canvas.getWidth() && player2.pos.x>0 && player2.pos.y<=canvas.getHeight() && player2.pos.y>0)
                player2.moveForward();
            if(player2.pos.x>canvas.getWidth()){
                player2.pos.x=15;
            }
            if(player2.pos.x<=0){
                player2.pos.x=canvas.getWidth();
            }
            if(player2.pos.y>canvas.getHeight()){
                player2.pos.y=canvas.getHeight();
            }
            if(player2.pos.y<=0) player2.pos.y=5;
        }
        if (leftPressed) {
            player2.changeAngle(-3);
        }
        if (downPressed) {
            if(player2.pos.x<=canvas.getWidth() && player2.pos.x>0 && player2.pos.y<=canvas.getHeight() && player2.pos.y>0)
                player2.moveBackward();
            if(player2.pos.x>canvas.getWidth()){
                player2.pos.x=15;

            }
            if(player2.pos.x<=0){
                player2.pos.x=canvas.getWidth();
            }
            if(player2.pos.y>canvas.getHeight()){
                player2.pos.y=canvas.getHeight();
            }
            if(player2.pos.y<=0) player2.pos.y=5;
        }
        if (rightPressed) {
            player2.changeAngle(3);
        }
    }

    /*
    public boolean detectionOfCollisionWalls() {
        for (Obstacle ob: obstacles) {
            double c1 = player1.pos.x -ob.x;
            double c2 = player1.pos.y -ob.y;
            double distancePlayer1 = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
            if (distancePlayer1 < 25) {
                return false;
            }
        }
    }
    */

}
