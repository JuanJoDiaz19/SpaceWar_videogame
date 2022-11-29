package com.example.tank_battle.model;

import com.example.tank_battle.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {
    private Canvas canvas;
    private GraphicsContext gc;
    public Vector pos;
    public Vector direction;
    private Image bulletImage;
    private int type;
    private Circle hitBox;

    public Bullet(Canvas canvas, GraphicsContext gc, Vector pos, Vector direction, int type) {
        this.canvas = canvas;
        this.gc = gc;
        this.pos = pos;
        this.direction = direction;
        this.type = type;
        String uri;
        if (type == 1) {
            uri =  "file:"+ GameMain.class.getResource("redBullet.png").getPath();
        } else {
            uri =  "file:"+ GameMain.class.getResource("blueBullet.png").getPath();
        }
        bulletImage = new Image(uri);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90 + direction.getAngle());
        hitBox = new Circle(pos.x, pos.y, 10);
        gc.drawImage(bulletImage,-15,-20,20,20);
        gc.restore();
        pos.x += direction.x;
        pos.y += direction.y;
    }

    public Circle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Circle hitBox) {
        this.hitBox = hitBox;
    }
}
