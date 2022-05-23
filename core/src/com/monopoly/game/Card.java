package com.monopoly.game;

public class Card {
    String text;
    int type;
    int id;
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
