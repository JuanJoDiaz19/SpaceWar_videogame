package com.example.tank_battle.control;
import com.example.tank_battle.model.Player;

public class Singleton {
    private static Singleton instance;

    public Player player1;
    public Player player2;
    public Player player3;

    private Singleton(){
        this.player1=new Player();
        this.player2=new Player();
        this.player3=new Player();
    }

    public static Singleton getInstance(){
        if(instance==null){
            instance= new Singleton();
        }

        return instance;
    }
    public void createPlayers(String name1,String name2,String name3){
        this.player1.setName(name1);
        this.player2.setName(name2);
        this.player3.setName(name3);
    }

    public Player getPlayer1(){
        return player1;
    }
    
    public void setPlayer1(Player player1){
        this.player1= player1;
    }

    public Player getPlayer2(){return player2;}

    public void setPlayer2(Player player2){
        this.player2= player2;
    }




}

