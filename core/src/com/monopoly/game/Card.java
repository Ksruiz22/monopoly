package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.monopoly.game.utils.CircularList;

import java.io.BufferedReader;
import java.io.FileReader;

public class Card {

    public String text;
    public int type;
    public int id;
    public int dinero;
    public Card(int id, String text , int type, int dinero){
        this.text = text;
        this.type = type;
        this.id = id;
        this.dinero = dinero;
    }
    public String getText(){
        return this.text;
    }






}
