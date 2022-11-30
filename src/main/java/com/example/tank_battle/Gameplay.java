package com.example.tank_battle;

import com.example.tank_battle.control.Singleton;
import com.example.tank_battle.efects.Sound;
import com.example.tank_battle.model.*;
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
import javafx.scene.text.Font;
import javafx.util.Pair;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Gameplay implements Initializable {
    @FXML
    private HBox hBox;
    @FXML
    private Canvas canvas;
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

    private GraphicsContext gc;

    private boolean isRunning = true;

    //Players in the game
    private Player player1;
    private Player player2;
    private Player player3;

    //Bullets in the game
    private ArrayList<Pair<Integer,Integer>> walletsp;
    private ArrayList<Bullet> bulletsPlayer1;
    private ArrayList<Bullet> bulletsPlayer2;
    private ArrayList<Bullet> bulletsPlayer3;

    //sounds
    private Sound sound1;
    
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

    private Image withRedBullet;
    private Image withBlueBullet;
    private Image withoutBullet;
    private Image withYellowBullet;
    private Image withoutHealth;

    private Image exp1;
    private Image exp2;
    private Image exp3;
    
    private boolean isPlayer1Exploded;
    private boolean isPlayer2Exploded;
    private boolean isPlayer3Exploded;
    private int animationExplosionPlayer1;
    private  int animationExplosionPlayer2;

    private int animationExplosionPlayer3;
    private double distanceP3P1;
    private double distanceP3P2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFonts();

        labelP1.setText(Singleton.getInstance().player1.getName());
        labelP2.setText(Singleton.getInstance().player2.getName());
        labelCPU.setText(Singleton.getInstance().player3.getName());

        isPlayer1Exploded = false;
        isPlayer2Exploded = false;
        isPlayer3Exploded = false;
        animationExplosionPlayer1 = 12;
        animationExplosionPlayer2 = 12;
        animationExplosionPlayer3 = 12;

        //Loading the images of the bullets
        withRedBullet = new Image("file:"+ GameMain.class.getResource("redBulletH.png").getPath());
        withBlueBullet= new Image("file:"+ GameMain.class.getResource("blueBulletH.png").getPath());
        withYellowBullet= new Image("file:"+ GameMain.class.getResource("blueBulletH.png").getPath());
        withoutBullet= new Image("file:"+ GameMain.class.getResource("emptyBulletH.png").getPath());
        withoutHealth = new Image("file:"+ GameMain.class.getResource("noLife.png").getPath());
        //Loading the images of the explotion

        exp1 = new Image("file:"+ GameMain.class.getResource("exp1.png").getPath());
        exp2 = new Image("file:"+ GameMain.class.getResource("exp2.png").getPath());
        exp3 = new Image("file:"+ GameMain.class.getResource("exp3.png").getPath());

        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        String uriBackground = "file:"+ GameMain.class.getResource("gameplayBackground.png").getPath();
        backgroud = new Image(uriBackground);

        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        //Initializing the bullets in the game

        bulletsPlayer1 = new ArrayList<>();
        bulletsPlayer2 = new ArrayList<>();
        bulletsPlayer3 = new ArrayList<>();
        obstacles = new ArrayList<>();
        walletsp= new ArrayList<>();

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
        Singleton.getInstance().getPlayer1().setGc(canvas.getGraphicsContext2D());
        Singleton.getInstance().getPlayer1().setTank("tank1.png");
        Singleton.getInstance().getPlayer2().setGc(canvas.getGraphicsContext2D());
        Singleton.getInstance().getPlayer2().setTank("tank2.png");
        Singleton.getInstance().getPlayer3().setGc(canvas.getGraphicsContext2D());
        Singleton.getInstance().getPlayer3().setTank("tank3.png");


        player1=Singleton.getInstance().getPlayer1();
        player2=Singleton.getInstance().getPlayer2();
        player3 = Singleton.getInstance().getPlayer3();

        player1.numLifes=5;
        player2.numLifes=5;
        player3.numLifes=5;

        player1.numBullets=5;
        player2.numBullets=5;
        player3.numBullets=5;

        player1.pos.x=250;
        player1.pos.y=250;
        player2.pos.x=750;
        player2.pos.y=250;
        player3.pos.x=500;
        player3.pos.y=500;

        player1.direction.x=1;
        player1.direction.y=0;
        player2.direction.x=-1;
        player2.direction.y=0;
        player3.direction.x=0;
        player3.direction.y=1;
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
                            if (animationExplosionPlayer1 > 11){
                                player1.draw();
                            }
                            if(animationExplosionPlayer2 > 11 ){
                                player2.draw();
                            }
                            if(animationExplosionPlayer3 > 11 ){
                                player3.draw();
                            }
                            for (Obstacle ob : obstacles) {
                                ob.draw();
                            }
                            for (int i = 0; i < bulletsPlayer1.size() ; i++) {
                                bulletsPlayer1.get(i).draw();
                            }
                            for (int i = 0; i < bulletsPlayer2.size() ; i++) {
                                bulletsPlayer2.get(i).draw();
                            }
                            for (int i = 0; i < bulletsPlayer3.size() ; i++) {
                                bulletsPlayer3.get(i).draw();
                            }

                            if(detectEndGame()){
                                isRunning = false;
                            }
                            if(!isRunning){
                                ending();
                            }


                            controlAI();
                            //Explosion animation
                            explosionAnimation();
                            //Colisiones
                            detectionBulletCollisionWithPlayer();
                            doKeyboardActions();
                            detectedCollision();
                            //bulletInPlayer1();
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

        new Thread(
                () -> {
                    while (isRunning) {
                        Platform.runLater(() ->{
                        });
                        //Sleep (Mimiendo)
                        try {
                            Thread.sleep(1000);
                            bulletsAI();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
        new Thread(
                () -> {
                    while (isRunning) {
                        Platform.runLater(() ->{
                        });
                        //Sleep (Mimiendo)
                        try {
                            Thread.sleep(3500);
                            if(player3.numBullets==0){
                                player3.numBullets=5;
                                b1cpu.setImage(withYellowBullet);
                                b2cpu.setImage(withYellowBullet);
                                b3cpu.setImage(withYellowBullet);
                                b4cpu.setImage(withYellowBullet);
                                b5cpu.setImage(withYellowBullet);
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();
    }
    private void ending() {
        String out="";
        if (animationExplosionPlayer1 <1 && animationExplosionPlayer2 < 1) {
            out=Singleton.getInstance().getPlayer3().getName();
        } else if (animationExplosionPlayer2 <1 && animationExplosionPlayer3 < 1){
            out=Singleton.getInstance().getPlayer1().getName();
        } else if (animationExplosionPlayer3 < 1 && animationExplosionPlayer1 <1) {
            out=Singleton.getInstance().getPlayer2().getName();

        }
        Scoreboard.getInstance().loadInformation();
        Scoreboard.getInstance().insert(new Player(out));
        if(!out.equals("")){
            Singleton.getInstance().winningTeam=out;
            GameMain.hideWindow("winning.fxml");
            GameMain.showTransparentWindow("winning.fxml");
        }
    }
    private boolean detectEndGame() {
        if (animationExplosionPlayer1 <1 && animationExplosionPlayer2 < 1) {
            return true;
        } else if (animationExplosionPlayer2 <1 && animationExplosionPlayer3 < 1){
            return true;
        } else if (animationExplosionPlayer3 < 1 && animationExplosionPlayer1 <1) {
            return true;
        }
        return false;
    }

    private void explosionAnimation() {
        if (isPlayer1Exploded && animationExplosionPlayer1 > 0){
            if(animationExplosionPlayer1 > 8){
                gc.drawImage(exp1, player1.pos.x-40, player1.pos.y-40, 80, 80);
            } else if (animationExplosionPlayer1 > 4) {
                gc.drawImage(exp2, player1.pos.x-40, player1.pos.y-40, 80, 80);
            } else {
                gc.drawImage(exp3, player1.pos.x-40, player1.pos.y-40, 80, 80);
            }
            animationExplosionPlayer1--;
        }
        if (isPlayer2Exploded && animationExplosionPlayer2 > 0){
            if(animationExplosionPlayer2 > 8){
                gc.drawImage(exp1, player2.pos.x-40, player2.pos.y-40, 80, 80);
            } else if (animationExplosionPlayer2 > 4) {
                gc.drawImage(exp2, player2.pos.x-40, player2.pos.y-40, 80, 80);
            } else {
                gc.drawImage(exp3, player2.pos.x-40, player2.pos.y-40, 80, 80);
            }
            animationExplosionPlayer2--;
        }
        if (isPlayer3Exploded && animationExplosionPlayer3 > 0){
            if(animationExplosionPlayer3 > 8){
                gc.drawImage(exp1, player3.pos.x-40, player3.pos.y-40, 80, 80);
            } else if (animationExplosionPlayer1 > 4) {
                gc.drawImage(exp2, player3.pos.x-40, player3.pos.y-40, 80, 80);
            } else {
                gc.drawImage(exp3, player3.pos.x-40, player3.pos.y-40, 80, 80);
            }
            animationExplosionPlayer3--;
        }
    }

    private void detectionBulletCollisionWithPlayer() {
        for (Bullet b : bulletsPlayer1) {
            //Cuando choca con player 2
            double c1 = b.pos.x - player2.pos.x;
            double c2 = b.pos.y - player2.pos.y;
            double distance = Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
            if(distance < 20){
                bulletsPlayer1.remove(b);
                player2.numLifes--;
                if (player2.numLifes == 0)isPlayer2Exploded = true;
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();
                switch (player2.numLifes){
                        case 4:
                            h5p2.setImage(withoutHealth);
                            break;
                        case 3:
                            h4p2.setImage(withoutHealth);
                            break;
                        case 2:
                            h3p2.setImage(withoutHealth);
                            break;
                        case 1:
                            h2p2.setImage(withoutHealth);
                            break;
                        case 0:
                            h1p2.setImage(withoutHealth);
                            break;
                }
            }
            //Cuando choca con Player 3
            double p3c1 = b.pos.x - player3.pos.x;
            double p3c2 = b.pos.y - player3.pos.y;
            double p3distance = Math.sqrt(Math.pow(p3c1, 2) + Math.pow(p3c2, 2));
            if(p3distance < 20){
                bulletsPlayer1.remove(b);
                player3.numLifes--;
                if (player3.numLifes == 0)isPlayer3Exploded = true;
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();
                switch (player3.numLifes){
                    case 4:
                        h5cpu.setImage(withoutHealth);
                        break;
                    case 3:
                        h4cpu.setImage(withoutHealth);
                        break;
                    case 2:
                        h3cpu.setImage(withoutHealth);
                        break;
                    case 1:
                        h2cpu.setImage(withoutHealth);
                        break;
                    case 0:
                        h1cpu.setImage(withoutHealth);
                        break;
                }
            }
        }

        for (Bullet b : bulletsPlayer2) {
            //Distancia con player 1
            double p1c1 = b.pos.x - player1.pos.x;
            double p1c2 = b.pos.y - player1.pos.y;
            double p1distance = Math.sqrt(Math.pow(p1c1, 2) + Math.pow(p1c2, 2));
            if(p1distance < 20){
                bulletsPlayer2.remove(b);
                player1.numLifes--;
                if (player1.numLifes == 0 )isPlayer1Exploded = true;
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();

                switch (player1.numLifes){
                    case 4:
                        h5p1.setImage(withoutHealth);
                        break;
                    case 3:
                        h4p1.setImage(withoutHealth);
                        break;
                    case 2:
                        h3p1.setImage(withoutHealth);
                        break;
                    case 1:
                        h2p1.setImage(withoutHealth);
                        break;
                    case 0:
                        h1p1.setImage(withoutHealth);
                        break;
                }
                return;
            }
            //Distancia con player 3
            double p3c1 = b.pos.x - player3.pos.x;
            double p3c2 = b.pos.y - player3.pos.y;
            double p3distance = Math.sqrt(Math.pow(p3c1, 2) + Math.pow(p3c2, 2));
            if(p3distance < 20){
                bulletsPlayer2.remove(b);
                player3.numLifes--;
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();
                if (player3.numLifes == 0 )isPlayer3Exploded = true;
                switch (player3.numLifes){
                    case 4:
                        h5cpu.setImage(withoutHealth);
                        break;
                    case 3:
                        h4cpu.setImage(withoutHealth);
                        break;
                    case 2:
                        h3cpu.setImage(withoutHealth);
                        break;
                    case 1:
                        h2cpu.setImage(withoutHealth);
                        break;
                    case 0:
                        h1cpu.setImage(withoutHealth);
                        break;
                }
                return;
            }
        }

        for (Bullet b : bulletsPlayer3) {
            //Choque con player 1
            double p1c1 = b.pos.x - player1.pos.x;
            double p1c2 = b.pos.y - player1.pos.y;
            double p1distance = Math.sqrt(Math.pow(p1c1, 2) + Math.pow(p1c2, 2));
            if(p1distance < 20){
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();
                bulletsPlayer3.remove(b);
                player1.numLifes--;
                if (player1.numLifes == 0)isPlayer1Exploded = true;
                switch (player1.numLifes){
                    case 4:
                        h5p1.setImage(withoutHealth);
                        break;
                    case 3:
                        h4p1.setImage(withoutHealth);
                        break;
                    case 2:
                        h3p1.setImage(withoutHealth);
                        break;
                    case 1:
                        h2p1.setImage(withoutHealth);
                        break;
                    case 0:
                        h1p1.setImage(withoutHealth);
                        break;
                }
            }
        }
        for (Bullet b : bulletsPlayer3) {
            //Choque con player 2
            double p2c1 = b.pos.x - player2.pos.x;
            double p2c2 = b.pos.y - player2.pos.y;
            double p2distance = Math.sqrt(Math.pow(p2c1, 2) + Math.pow(p2c2, 2));
            if( p2distance < 20){
                Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                Singleton.getInstance().playSound();
                bulletsPlayer3.remove(b);
                player2.numLifes--;
                if (player2.numLifes == 0)isPlayer2Exploded = true;
                switch (player2.numLifes){
                    case 4:
                        h5p2.setImage(withoutHealth);
                        break;
                    case 3:
                        h4p2.setImage(withoutHealth);
                        break;
                    case 2:
                        h3p2.setImage(withoutHealth);
                        break;
                    case 1:
                        h2p2.setImage(withoutHealth);
                        break;
                    case 0:
                        h1p2.setImage(withoutHealth);
                        break;
                }
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
        if(keyEvent.getCode() == KeyCode.SPACE && player1.numBullets > 0 && player1.numLifes >0) {
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
        if(keyEvent.getCode() == KeyCode.SHIFT && player2.numBullets > 0 && player2.numLifes >0) {
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
            b1p1.setImage(withRedBullet);
            b2p1.setImage(withRedBullet);
            b3p1.setImage(withRedBullet);
            b4p1.setImage(withRedBullet);
            b5p1.setImage(withRedBullet);
        }
        if(keyEvent.getCode() == KeyCode.CONTROL){
            player2.numBullets = 5;
            player1.numBullets = 5;
            b1p2.setImage(withBlueBullet);
            b2p2.setImage(withBlueBullet);
            b3p2.setImage(withBlueBullet);
            b4p2.setImage(withBlueBullet);
            b5p2.setImage(withBlueBullet);
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
                    Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                    Singleton.getInstance().playSound();
                }
            }
        }

        for (Obstacle o: obstacles) {
            for (Bullet b: bulletsPlayer2) {
                if (b.getHitBox().intersects( o.getY(), o.getX(),50, 50)){
                    bulletsPlayer2.remove(b);
                    obstacles.remove(o);
                    Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                    Singleton.getInstance().playSound();
                }
            }
        }
        for (Obstacle o: obstacles) {
            for (Bullet b: bulletsPlayer3) {
                if (b.getHitBox().intersects( o.getY(), o.getX(),50, 50)){
                    bulletsPlayer3.remove(b);
                    obstacles.remove(o);
                    Singleton.getInstance().setMusicPath("src/main/resources/com/example/tank_battle/explosion.wav");
                    Singleton.getInstance().playSound();
                }
            }
        }

    }



   InputStream is = getClass().getResourceAsStream("/fonts/Pixeboy.ttf");
    private Font pixeman21 = Font.loadFont(is, 21.0);

    public void bulletsAI(){
        boolean isNear = distanceP3P1 < 100 || distanceP3P2 < 100;
        if((player3.isColliding || isNear) && player3.numBullets>0&& !isPlayer3Exploded){
            System.out.println("Entro a bullets ia");
            Bullet bullet = new Bullet(canvas, gc, new Vector(player3.pos.x, player3.pos.y ), new Vector(2.5*player3.direction.x, 2.5*player3.direction.y ), 3);
            bulletsPlayer3.add(bullet);
            player3.numBullets--;
            switch (player3.numBullets){
                case 4:
                    b5cpu.setImage(withoutBullet);
                    break;
                case 3:
                    b4cpu.setImage(withoutBullet);
                    break;
                case 2:
                    b3cpu.setImage(withoutBullet);
                    break;
                case 1:
                    b2cpu.setImage(withoutBullet);
                    break;
                case 0:
                    b1cpu.setImage(withoutBullet);
                    break;
            }
        }
    }
    public void controlAI(){
        double distancep1;
        if (!isPlayer1Exploded){
            double dxp1=player1.pos.x-player3.pos.x;
            double dyp1=player1.pos.y-player3.pos.y;
            distancep1 = Math.sqrt(Math.pow(dxp1, 2) + Math.pow(dyp1, 2));
        } else {
            distancep1 = Double.POSITIVE_INFINITY;
        }
        double distancep2;
        if(!isPlayer2Exploded){
            double dxp2=player2.pos.x-player3.pos.x;
            double dyp2=player2.pos.y-player3.pos.y;
            distancep2 = Math.sqrt(Math.pow(dxp2, 2) + Math.pow(dyp2, 2));
        } else {
            distancep2 = Double.POSITIVE_INFINITY;
        }
        double componentX,componentY;
        double rule =0;
        //System.out.println(isPlayer1Exploded);
        //System.out.println(isPlayer2Exploded);
        if(distancep1<=distancep2){


            componentX = (player1.pos.x-player3.pos.x);
            componentY = (player1.pos.y-player3.pos.y);
            rule = Math.sqrt(Math.pow(componentX, 2) + Math.pow(componentY, 2));
            player3.direction.x=componentX/rule;
            player3.direction.y=componentY/rule;


        }else if (distancep1 >= distancep2){
            componentX = (player2.pos.x-player3.pos.x);
            componentY = (player2.pos.y-player3.pos.y);
            rule = Math.sqrt(Math.pow(componentX, 2) + Math.pow(componentY, 2));
            player3.direction.x=componentX/rule;
            player3.direction.y=componentY/rule;
        }
        player3.isColliding=false;
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).getHitBox().intersects(player3.pos.x- player3.direction.x -15 , player3.pos.y- player3.direction.y -15, 30, 30 )){
                player3.isColliding=true;
            }
        }
        if (!player3.isColliding&&distancep1>60&&distancep2>60&&(!isPlayer1Exploded||!isPlayer2Exploded)) player3.moveForward();
        if (isPlayer1Exploded&&isPlayer2Exploded) player3.changeAngle(5);
        distanceP3P1 = distancep1;
        distanceP3P2 = distancep2;
        //if(distancep1<100||distancep2<100) bulletsAI();
    }

    private void setFonts(){
        labelP1.setFont(pixeman21);
        labelP2.setFont(pixeman21);
        labelCPU.setFont(pixeman21);
    }

}
