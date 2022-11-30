package com.example.tank_battle.control;
import com.example.tank_battle.GameMain;
import com.example.tank_battle.model.Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Singleton {
    private static Singleton instance;
    public Player player1;
    public Player player2;
    public Player player3;
    public String winningTeam;

    public AudioInputStream getAudioInput() {
        return audioInput;
    }

    public void setAudioInput(AudioInputStream audioInput) {
        this.audioInput = audioInput;
    }

    private AudioInputStream audioInput;

    public File getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String path) {
        this.musicPath = new File(path);
    }

    private File musicPath;
    private Clip clip;

    private Singleton(){
        this.player1=new Player(1);
        this.player2=new Player(2);
        this.player3=new Player(3);
        this.player3.isEnemy=true;

        //String uri = GameMain.class.getResource("explosion.wav").getPath();
        musicPath = new File("src/main/resources/com/example/tank_battle/explosion.wav");

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

    public void playSound(){
        if(musicPath.exists()){
            try {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(1);
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("No existe");
        }
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
    public Player getPlayer3(){return player3;}
}

