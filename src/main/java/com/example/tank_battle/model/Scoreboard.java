package com.example.tank_battle.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scoreboard {

    ArrayList<Player> players = new ArrayList<>();
    public static Scoreboard instance = new Scoreboard();

    public Scoreboard() {

    }

    public void insert(Player p){

        if(search(p)==null){
            players.add(p);
        }else wonAGame(search(p));;
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getGamesWon() - o2.getGamesWon();
            }
        });
        Collections.reverse(players);

    }

    public Player search(Player p){
        for(Player x : players){
            if(p.getName().equals(x.getName()))
                return x;
        }
        return null;
    }

    public void wonAGame(Player p){
        p.setGamesWon(p.getGamesWon()+1);
    }

    public ArrayList<Player> getPlayers() {
        loadInformation();
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public static Scoreboard getInstance() {
        return instance;
    }

    public void loadInformation() {
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        File archivo = new File("leaderBoard.txt");
        FileInputStream fis  = null;
        try {
            fis = new FileInputStream(archivo);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String parts[] = line.split(" ");
                Player p = new Player(parts[0]);
                p.setGamesWon(p.getGamesWon() + Integer.parseInt( parts[1]) -1);
                newPlayers.add(p);
            }
            fis.close();
            players = newPlayers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInformation(){
        String text = "";
        for (Player p : players) {
            text+=(p.getName() + p.getGamesWon() + "\n");
        }
        File file = new File("leaderBoard.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

