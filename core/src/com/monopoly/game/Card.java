package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.monopoly.game.utils.CircularList;

import java.io.BufferedReader;
import java.io.FileReader;

public class Card {

    public String text;
    public int type;
    public int id;
    public Card(int id, String text , int type){
        this.text = text;
        this.type = type;
        this.id = id;
    }
    public String getText(){
        return this.text;
    }

    public void giveMoney(Player player, int amount){
        player.añadirCantidad(amount, this.text);
    }

    public void removeMoney(Player player, int amount){

    }

    public void GoToJail(Player player){
        player.veCarcel();
    }

    public void outJail(){

    }



}
