package com.monopoly.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.monopoly.game.Assets;
import com.monopoly.game.Screens;
import com.monopoly.game.monopoly;
import com.monopoly.game.utils.Game;


public class MainMenuScreen extends Screens {

    ScrollPane scroll;

    public MainMenuScreen(monopoly game) {
        super(game);
        Table menu = new Table();
        menu.setFillParent(true);
        menu.defaults().uniform().fillY();

        for(final Game option : Game.values()){
            TextButton bt = new TextButton(option.name, Assets.txButtonStyle);
            bt.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MainMenuScreen.this.game.setScreen(getScreen(option));
                }
            });

            menu.row().padTop(20).height(50);
            menu.add(bt).size(SCREEN_WIDTH / 6, SCREEN_HEIGHT / 8).fillX();
        }

        scroll = new ScrollPane(menu, Assets.scrollPaneStyle);
        scroll.setSize(500,SCREEN_HEIGHT/1.5f);
        scroll.setPosition(550,0);
        stage.addActor(scroll);
    }

    private Screens getScreen(Game option){
        switch (option){
            default:
                return  new OptionsScreen(game);
            case INFO:
                return new InfoScreen(game);
            case EXIT:
                Gdx.app.exit();
                return null;
        }
    }

    @Override
    public void draw(float delta) {
        backgroundImageSprite.draw(spriteBatch);
        backgroundTitleSprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {

    }

}
