package com.example.tank_battle.model;

import java.util.ArrayList;

public class Scoreboard {

    private Player root;
    ArrayList<Pair<String, Integer>> players = new ArrayList<>();
    public static Scoreboard instance = new Scoreboard();

    public Scoreboard() {

    }

    public void insert(Player current) {
        if (root == null) {
            root = current;
        } else {
            insert(current, root);
        }
    }

    private void insert(Player player, Player current) {
        if (player.getWonMatches() == 0) return;
        if (player.getWonMatches() == current.getWonMatches()) {
            //si es igual, en este caso lo guardamos en el arraylist de nombres del nodo con el mismo score
            current.addArray(player.getName());

        }
        if (player.getWonMatches() < current.getWonMatches()) {
            //izauierda
            if (current.getLeft() != null) {
                insert(player, current.getLeft());
            } else {
                current.setLeft(player);
            }
        } else if (player.getWonMatches() > current.getWonMatches()) {
            //derecha
            if (current.getRight() != null) {
                insert(player, current.getRight());
            } else {
                current.setRight(player);
            }
        }
    }

    public ArrayList<Pair<String, Integer>> printScore(Player current, int n) {
        if (current == null) {
            return players;
        }
        printScore(current.getRight(), n);
        players.add(new Pair<>(current.getName(), current.getWonMatches()));
        if (!current.empty()) {
            for (String x : current.getNames()) {
                players.add(new Pair<>(x, current.getWonMatches()));
            }
        }

        n++;

        return printScore(current.getLeft(), n);
    }

    public Player getRoot() {
        return root;
    }

    public void setRoot(Player root) {
        this.root = root;
    }

    public ArrayList<Pair<String, Integer>> updateLeaderboard(){
        players.clear();
        return printScore(root, 1);
    }

    public static Scoreboard getInstance() {
        return instance;
    }

}

