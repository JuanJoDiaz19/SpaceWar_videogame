package com.example.tank_battle.model;

public class Vector {
    public double x;
    public double y;

    public Vector(double x,double y){
        this.x = x;
        this.y = y;
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

    public double getAmplitude(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
