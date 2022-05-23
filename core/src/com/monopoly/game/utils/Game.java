package com.monopoly.game.utils;

public enum Game {
    GAME("Jugar"),
    INFO("Información"),
    EXIT("Salir");

    public final String name;
    Game(String label){
        this.name=label;
    }
}
