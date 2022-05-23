package com.monopoly.game;

import com.badlogic.gdx.Game;
import com.monopoly.game.Pantallas.MainMenuScreen;

public class monopoly extends Game {

    @Override
    public void create() {
        Assets.load();
        setScreen(new MainMenuScreen(this));
    }
}
