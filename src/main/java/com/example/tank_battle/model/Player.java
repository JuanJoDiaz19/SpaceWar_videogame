package com.example.tank_battle.model;

import com.example.tank_battle.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    private String name;
    private int wonMatches;

    private Canvas canvas;
    private GraphicsContext gc;
    private Image tank;
    public Vector pos;
    public Vector direction;

    public Player(String name,Canvas canvas){
        this.name=name;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ GameMain.class.getResource("tank1.png").getPath();
        tank = new Image(uri);
        pos = new Vector(100, 100);
        direction = new Vector(1,1);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 50,50);
        gc.restore();
    }

    public void changeAngle(double a){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        angle += a;
        direction.x = amp*Math.cos(Math.toRadians(angle));
        direction.y = amp*Math.sin(Math.toRadians(angle));
    }


    public void moveForward(){
        pos.x += direction.x;
        pos.y += direction.y;
    }

    public void moveBackward() {
        pos.x -= direction.x;
        pos.y -= direction.y;
    }

    public Vector getPos(){
        return pos;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getWonMatches(){
        return wonMatches;
    }

    public void setWonMatches(int wonMatches){
        this.wonMatches = wonMatches;
    }

}
