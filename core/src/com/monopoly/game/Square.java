package com.monopoly.game;

public abstract class Square {
    public String name;
    public int squareId;

    public Square(String name, int squareId){
        this.name = name;
        this.squareId = squareId;
    }

    public int getSquareId(){
        return this.squareId;
    }

    public String getName(){
        return name;
    }
}
