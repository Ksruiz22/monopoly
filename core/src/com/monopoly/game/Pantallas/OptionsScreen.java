package com.monopoly.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.monopoly.game.Assets;
import com.monopoly.game.Screens;
import com.monopoly.game.monopoly;

import java.util.ArrayList;
import java.util.List;

public class OptionsScreen extends Screens {
    int players = 0;
    CheckBox checkBoxA, checkBoxB, checkBoxC, checkBoxD;
    List playersSkin = new ArrayList();
    Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

    public OptionsScreen(final monopoly game) {
        super(game);

        Image logo = new Image(Assets.logoTexture);
        //Player images
        Image shipPlayer = new Image(Assets.shipTexture);
        Image hatPlayer = new Image(Assets.hatTexture);
        Image dogPlayer = new Image(Assets.dogTexture);
        final Image carPlayer = new Image(Assets.carTexture);


        Container<Table> tableContainer = new Container<Table>();

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        float cw = sw * 0.5f;
        float ch = sh;

        tableContainer.setSize(cw, ch);
        tableContainer.setPosition((sw/2)-(tableContainer.getWidth()/2), 0);
        tableContainer.fillX();

        Table table = new Table(skin);
        table.align(Align.center);

        Label topLabel = new Label("GAME OPTIONS", Assets.labelTitlesStyle);
        topLabel.setAlignment(Align.center);

        Label anotherLabel = new Label("Players", Assets.labelStyle);
        anotherLabel.setAlignment(Align.center);

        checkBoxA = new CheckBox("Dog", skin);
        checkBoxB = new CheckBox("Ship", skin);
        checkBoxC = new CheckBox("Car", skin);
        checkBoxD = new CheckBox("Hat", skin);
        Table buttonTable = new Table(skin);

        TextButton buttonA = new TextButton("Back", skin);
        TextButton buttonB = new TextButton("Play", skin);

        table.row().colspan(4).expandX().fillX();
        table.add(logo).width(208.25f).height(257.25f).padLeft(208.25f/3f);
        table.row().colspan(4).expandX().fillX();
        table.add(topLabel).fillX();
        table.row().colspan(4).expandX().fillX();
        table.add(anotherLabel).fillX();
        table.row().expandX().fillX();

        table.add(dogPlayer).width(50f).height(50f).expandX().fillX();
        table.add(shipPlayer).width(120f).height(50f).expandX().fillX();
        table.add(carPlayer).width(100f).height(50f).expandX().fillX();
        table.add(hatPlayer).width(50f).height(50f).expandX().fillX();
        table.row().expandX().fillX();

        table.add(checkBoxA).expandX().fillX();
        table.add(checkBoxB).expandX().fillX();
        table.add(checkBoxC).expandX().fillX();
        table.add(checkBoxD).expandX().fillX();
        table.row().expandX().fillX();

        table.add(buttonTable).colspan(4);

        buttonTable.pad(16);
        buttonTable.row().fillX().expandX();
        buttonTable.add(buttonA).width(cw/3.0f);
        buttonTable.add(buttonB).width(cw/3.0f);



        buttonA.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OptionsScreen.this.game.setScreen(new MainMenuScreen(game));
            }
        });

        buttonB.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                players = 0;
                playersSkin.clear();
                countPlayers();
                if(players < 2){
                    Dialog dialog = new Dialog("Error", skin, "dialog") {
                        public void result(Object obj) {
                        }
                    };
                    dialog.text("You need select 2 or more character for start");
                    dialog.button("Ok", true); //sends "false" as the result
                    dialog.show(stage);
                }else {
                    OptionsScreen.this.game.setScreen(new GameScreen(game, playersSkin));
                }

            }
        });

        tableContainer.setActor(table);
        stage.addActor(tableContainer);

    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void update(float delta) {

    }

    public void countPlayers(){
        if(checkBoxA.isChecked()){
            players = players + 1;
            playersSkin.add("dog");
        }
        if(checkBoxB.isChecked()){
            players = players + 1;
            playersSkin.add("ship");

        }
        if(checkBoxC.isChecked()){
            players = players + 1;
            playersSkin.add("car");
        }
        if(checkBoxD.isChecked()){
            players = players + 1;
            playersSkin.add("hat");
        }
    }
}
