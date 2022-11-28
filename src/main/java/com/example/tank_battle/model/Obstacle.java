package com.example.tank_battle.model;

import com.example.tank_battle.GameMain;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Obstacle {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image wall;

    private int x, y;

    public Obstacle(Canvas canvas, GraphicsContext gc, int x, int y) {
        this.canvas = canvas;
        this.gc = gc;
        this.x = 50 *x;
        this.y = 50 *y;
        String uri =  "file:"+ GameMain.class.getResource("wall.png").getPath();
    }

    public void draw(){
        //System.out.println(x+ " " +y);

        //gc.drawImage(wall ,x,y, 50,50   );

        gc.setFill(Color.BLUE);
        gc.fillRect(y ,x ,50,50);

    }

}
