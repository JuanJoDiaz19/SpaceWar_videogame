package com.example.tank_battle.model;

import com.example.tank_battle.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle {

    private Canvas canvas;
    private GraphicsContext gc;
    private Image wall;
    private Rectangle hitBox;
    private int x, y;

    public Obstacle(Canvas canvas, GraphicsContext gc, int x, int y, int type) {
        this.canvas = canvas;
        this.gc = gc;
        this.x = 50 *x;
        this.y = 50 *y;
        String uri;
        if (type == 1) {
            uri =  "file:"+ GameMain.class.getResource("wall3.png").getPath();
        } else {
            uri =  "file:"+ GameMain.class.getResource("wall2.png").getPath();
        }
        wall = new Image(uri);
    }

    public void draw(){
        hitBox = new Rectangle(y+5, x+5, 40, 40);
        gc.drawImage(wall, y,x, 50,50);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
}
