package com.example.tank_battle.efects;

import com.example.tank_battle.GameMain;


public class Sound {
    //private Media

    public Sound(String path){
        String uri = "file:"+ GameMain.class.getResource(path).getPath();
    }

}
