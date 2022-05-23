package com.monopoly.game;


public class Tax extends Square {
    int amount;
    public Tax(String name, int squareId, int amount) {
        super(name, squareId);
        this.amount = amount;
    }
}
