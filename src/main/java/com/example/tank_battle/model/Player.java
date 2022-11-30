package com.example.tank_battle.model;

import com.example.tank_battle.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<String> names;
    private int wonMatches;
    private Player left;
    private Player right;
    private Canvas canvas;

    private GraphicsContext gc;
    private Image tank;
    public Vector pos;
    public Vector direction;
    public int numBullets;
    public int numLifes;
    public boolean isColliding=false;
    public boolean isEnemy;
    public int type;

    public Player(int type ){
        if(type == 1){
            pos = new Vector(250, 250);
            direction = new Vector(1,0);
        } else if (type == 2){
            pos = new Vector(750, 250);
            direction = new Vector(-1,0);
        } else {
            pos = new Vector(500, 500);
            direction = new Vector(0,1);
        }
        this.type = type;
        names = new ArrayList<>();
        numBullets = 5;
        numLifes = 5;
    }

    public Player(String name,Canvas canvas, String path){
        this.name=name;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+ GameMain.class.getResource(path).getPath();
        tank = new Image(uri);
    }

    public Player(String name, int wonMatches){
        this.name = name;
        this.wonMatches = wonMatches;
        names = new ArrayList<>();
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        if (type != 3){
            gc.drawImage(tank, -20,-20, 40,40);
        } else {
            gc.drawImage(tank, -30,-30, 50,50);
        }

        gc.restore();
    }

    public void unDraw(){
        gc.save();
        gc.drawImage(null, 0,0,0,0);
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

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public void addArray(String value){
        names.add(value);
    }

    public boolean empty() {
        return names.isEmpty();
    }

    public ArrayList<String> getNames(){
        return new ArrayList<>(names);
    }
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public void setTank(String path) {
        String uri = "file:"+ GameMain.class.getResource(path).getPath();
        this.tank = new Image(uri);
    }
    public Image getTank() {
        return tank;
    }
}
