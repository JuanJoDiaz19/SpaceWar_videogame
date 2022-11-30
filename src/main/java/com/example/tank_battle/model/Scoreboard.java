package com.example.tank_battle.model;

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
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public static Scoreboard getInstance() {
        return instance;
    }

}

