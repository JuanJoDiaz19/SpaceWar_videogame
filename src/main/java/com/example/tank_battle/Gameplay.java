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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.File;
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
    private ArrayList<Pair<Integer,Integer>> walletsp;
    private ArrayList<Bullet> bulletsPlayer1;
    private ArrayList<Bullet> bulletsPlayer2;

    private int diedPlayer1;

    private int diedPlayer2;
    private Image backgroud;
    private Obstacle[][] obstaclesMap;
    private ArrayList<Obstacle> obstacles;
    @FXML
    private ImageView b1cpu;

    @FXML
    private ImageView b1p1;

    @FXML
    private ImageView b1p2;

    @FXML
    private ImageView b2cpu;

    @FXML
    private ImageView b2p1;

    @FXML
    private ImageView b2p2;

    @FXML
    private ImageView b3cpu;

    @FXML
    private ImageView b3p1;

    @FXML
    private ImageView b3p2;

    @FXML
    private ImageView b4cpu;

    @FXML
    private ImageView b4p1;

    @FXML
    private ImageView b4p2;

    @FXML
    private ImageView b5cpu;

    @FXML
    private ImageView b5p1;

    @FXML
    private ImageView b5p2;

    @FXML
    private ImageView h1cpu;

    @FXML
    private ImageView h1p1;

    @FXML
    private ImageView h1p2;

    @FXML
    private ImageView h2cpu;

    @FXML
    private ImageView h2p1;

    @FXML
    private ImageView h2p2;

    @FXML
    private ImageView h3cpu;

    @FXML
    private ImageView h3p1;

    @FXML
    private ImageView h3p2;

    @FXML
    private ImageView h4cpu;

    @FXML
    private ImageView h4p1;

    @FXML
    private ImageView h4p2;

    @FXML
    private ImageView h5cpu;

    @FXML
    private ImageView h5p1;

    @FXML
    private ImageView h5p2;

    @FXML
    private Label labelCPU;

    @FXML
    private Label labelP1;

    @FXML
    private Label labelP2;
    
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

    private Image withBullet;
    private Image withoutBullet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        withBullet= new Image("file:"+ GameMain.class.getResource("redBullet.png").getPath());
        withoutBullet= new Image("file:"+ GameMain.class.getResource("projectileEmpty.png").getPath());
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        String uriBackground = "file:"+ GameMain.class.getResource("gameplayBackground.png").getPath();
        backgroud = new Image(uriBackground);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        //Initializing the bullets in the game

        bulletsPlayer1 = new ArrayList<>();
        bulletsPlayer2 = new ArrayList<>();
        obstacles = new ArrayList<>();
        walletsp= new ArrayList<>();
        diedPlayer1=0;

        //Initializing the matrix
        Integer obstaclesInMap[][] = new Integer[][]{
                {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
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

                            for (Obstacle ob : obstacles) {
                                ob.draw();
                            }

                            for (int i = 0; i < bulletsPlayer1.size() ; i++) {
                                bulletsPlayer1.get(i).draw();
                            }
                            for (int i = 0; i < bulletsPlayer2.size() ; i++) {
                                bulletsPlayer2.get(i).draw();
                            }
                            //Colisiones
                            detectionBulletCollisionWithPlayer();
                            doKeyboardActions();
                            detectedCollision();
                            bulletInPlayer1();
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

    private void detectionBulletCollisionWithPlayer() {
        for (Bullet b : bulletsPlayer1) {
            double c1 = b.pos.x - player2.pos.x;
            double c2 = b.pos.y - player2.pos.y;
            double distance = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
            if(distance < 10){
                bulletsPlayer1.remove(b);
                player2.numLifes--;
                return;
            }
        }

        for (Bullet b : bulletsPlayer2) {
            double c1 = b.pos.x - player1.pos.x;
            double c2 = b.pos.y - player1.pos.y;
            double distance = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
            if(distance < 10){
                bulletsPlayer2.remove(b);
                player1.numLifes--;
                return;
            }
        }
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
            player1.numBullets--;
            switch (player1.numBullets){
                case 4:
                    b5p1.setImage(withoutBullet);
                    break;
                case 3:
                    b4p1.setImage(withoutBullet);
                    break;
                case 2:
                    b3p1.setImage(withoutBullet);
                    break;
                case 1:
                    b2p1.setImage(withoutBullet);
                    break;
                case 0:
                    b1p1.setImage(withoutBullet);
                    break;
            }
        }
        if(keyEvent.getCode() == KeyCode.SHIFT && player2.numBullets > 0) {
            Bullet bullet = new Bullet(canvas, gc, new Vector(player2.pos.x, player2.pos.y ), new Vector(2.5*player2.direction.x, 2.5*player2.direction.y ), 2);
            bulletsPlayer2.add(bullet);
            player2.numBullets--;
            switch (player2.numBullets){
                case 4:
                    b5p2.setImage(withoutBullet);
                    break;
                case 3:
                    b4p2.setImage(withoutBullet);
                    break;
                case 2:
                    b3p2.setImage(withoutBullet);
                    break;
                case 1:
                    b2p2.setImage(withoutBullet);
                    break;
                case 0:
                    b1p2.setImage(withoutBullet);
                    break;
            }
        }
        if(keyEvent.getCode() == KeyCode.E){
            player1.numBullets = 5;
            b1p1.setImage(withBullet);
            b2p1.setImage(withBullet);
            b3p1.setImage(withBullet);
            b4p1.setImage(withBullet);
            b5p1.setImage(withBullet);
        }
        if(keyEvent.getCode() == KeyCode.CONTROL){
            player2.numBullets = 5;
            player1.numBullets = 5;
            b1p2.setImage(withBullet);
            b2p2.setImage(withBullet);
            b3p2.setImage(withBullet);
            b4p2.setImage(withBullet);
            b5p2.setImage(withBullet);
        }
    }

    private void doKeyboardActions() {
        if (Wpressed) {
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0 && player1.pos.y<=canvas.getHeight() && player1.pos.y>0){
                boolean flag = false;
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).getHitBox().intersects(player1.pos.x+ player1.direction.x -15 , player1.pos.y+ player1.direction.y -15, 30, 30 )){
                        flag = true;
                    }
                }
                if (!flag) player1.moveForward();
            }

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
            if(player1.pos.x<=canvas.getWidth() && player1.pos.x>0 && player1.pos.y<=canvas.getHeight() && player1.pos.y>0){
                boolean flag = false;
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).getHitBox().intersects(player1.pos.x- player1.direction.x -15 , player1.pos.y- player1.direction.y -15, 30, 30 )){
                        flag = true;
                    }
                }
                if (!flag) player1.moveBackward();
            }
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
            if(player2.pos.x<=canvas.getWidth() && player2.pos.x>0 && player2.pos.y<=canvas.getHeight() && player2.pos.y>0){
                boolean flag = false;
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).getHitBox().intersects(player2.pos.x+ player2.direction.x -15 , player2.pos.y+ player2.direction.y -15, 30, 30 )){
                        flag = true;
                    }
                }
                if (!flag) player2.moveForward();
            }

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
            if(player2.pos.x<=canvas.getWidth() && player2.pos.x>0 && player2.pos.y<=canvas.getHeight() && player2.pos.y>0){
                boolean flag = false;
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).getHitBox().intersects(player2.pos.x- player2.direction.x -15 , player2.pos.y- player2.direction.y -15, 30, 30 )){
                        flag = true;
                    }
                }
                if (!flag) player2.moveBackward();
            }
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

    private void detectedCollision(){
        for (Obstacle o: obstacles) {
            for (Bullet b: bulletsPlayer1) {
                if (b.getHitBox().intersects( o.getY(), o.getX(),50, 50)){
                    bulletsPlayer1.remove(b);
                    obstacles.remove(o);
                }
            }
        }

        for (Obstacle o: obstacles) {
            for (Bullet b: bulletsPlayer2) {
                if (b.getHitBox().intersects( o.getY(), o.getX(),50, 50)){
                    bulletsPlayer2.remove(b);
                    obstacles.remove(o);
                }
            }
        }
    }

    private void bulletInPlayer1(){
        for(int i=0; i<bulletsPlayer2.size(); i++){
            Bullet b= bulletsPlayer2.get(i);

            double c1= b.pos.x-player1.pos.x;
            double c2= b.pos.y-player1.pos.y;
            double distance = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
            if(distance<27) {
                diedPlayer1++;
                if(diedPlayer1==3){
                    System.out.println("HPPPPP");
                    //player1=null;
                }
            }
        }
    }

}
