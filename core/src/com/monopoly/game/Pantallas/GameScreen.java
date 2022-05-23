
package com.monopoly.game.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.monopoly.game.Assets;
import com.monopoly.game.Board;
import com.monopoly.game.Player;
import com.monopoly.game.Screens;
import com.monopoly.game.monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class GameScreen extends Screens {
    static Skin skin;
    Pixmap bgPixmap;
    TextureRegionDrawable textureRegionDrawableBg;
    Image board, car, ship,
            hat, dog, dadoActual1, dadoActual2;
    Label.LabelStyle style;
    int cantPlayers;
    double dadoS1 = 1, dadoS2 = 1;
    List players = new ArrayList();
    List playersSort = new ArrayList();
    List counters = new ArrayList();
    Boolean start = false;
    int countTurns = 0;
    String actualPlayer = "";
    Label player;
    public GameScreen(monopoly game, final List players) {
        super(game);

        loadAssets();
        selectDiceImages();
        cantPlayers = players.size();
        this.players = players;



        Table table = new Table();
        table.defaults().pad(10F);
        table.setFillParent(true);


        player = new Label("Turn: "+actualPlayer,skin);
        player.setAlignment(Align.left);

        Label money = new Label("Money: 1000$",skin);
        money.setAlignment(Align.center);

        Label turn = new Label("Players: ",skin);
        turn.setAlignment(Align.left);

        TextButton Roll = new TextButton("Roll Dice", skin);
        TextButton End = new TextButton("End Turn", skin);
        TextButton Properties = new TextButton("com.monopoly.game.Properties", skin);
        TextButton Monopoly = new TextButton("Monopoly", skin);

        Table dados = new Table();
        dados.pad(5f);
        dados.setDebug(true);
        dados.add(player).colspan(2).fillX();
        dados.row();
        dados.add(dadoActual1);
        dados.add(dadoActual2).padLeft(10f);

        Table menu = new Table();
        menu.setDebug(true);
        menu.add(Roll).colspan(2).fillX().size(120,50);
        menu.row();
        menu.add(End).colspan(2).fillX().padTop(20F).size(120,50);
        menu.row();
        menu.add(money).colspan(2).fillX().padTop(20F).size(120,50);


        Table turns = new Table();
        turns.setDebug(true);
        turns.add(turn);

        if(players.contains("dog")){
            turns.add(dog).size(80,80).pad(10f);
        }
        if(players.contains("ship")){
            turns.add(ship).size(80,80).pad(10f);
        }
        if(players.contains("car")){
            turns.add(car).size(80,80).pad(10f);
        }
        if(players.contains("hat")){
            turns.add(hat).size(80,80).pad(10f);
        }


        Table top = new Table();
        top.pad(5f);
        top.align(Align.left);
        top.setDebug(true);
        top.add(turns).fillX();
        top.add(Monopoly).size(150,100).expand();

        Table first_table = new Table();
        first_table.defaults().pad(5F);
        first_table.setBackground(textureRegionDrawableBg);
        first_table.setDebug(true);
        first_table.defaults().pad(5F);
        first_table.add(dados).expand().fillX();
        first_table.row();
        first_table.add(menu).expand().height(Gdx.graphics.getHeight()*3/4).fillX();

        Table second_table= new Table();
        second_table.defaults().pad(5F);
        second_table.setBackground(textureRegionDrawableBg);
        second_table.add(top).fillX();
        second_table.row();
        second_table.add(board).expand().fillY();


        table.add(first_table).width(Gdx.graphics.getWidth()/4).fillY();
        table.add(second_table).expand().fillY();



        stage.addActor(table);
        stage.setDebugAll(true);

        Roll.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                girarDados();
                selectDiceImages();

                //Just the start
                if(countTurns <= players.size()){
                    counters.add(dadoS1+dadoS2);
                }
                if(countTurns == players.size()){
                    for(int i=0; i<players.size(); i++){
                        players.set(i, players.get(i)+"-"+counters.get(i));
                    }

                    Collections.sort(counters,Collections.reverseOrder());
                    for (int j=0; j<players.size(); j++) {
                        for (int i = 0; i < players.size(); i++) {
                            String player = (String) players.get(i);
                            String[] parts = player.split("-");
                            String f = ""+ counters.get(j);
                            if (f.contains(parts[1])) {
                                if(playersSort.contains(parts[0])){

                                }else{
                                    playersSort.add(parts[0]);
                                }

                            }
                        }
                    }
                    System.out.println(playersSort);
                    Dialog order = new Dialog("Start", skin, "dialog") {
                        public void result(Object obj) {
                            startGame();
                        }
                    };
                    order.text("The order goes: ");
                    for (int i = 0; i < playersSort.size(); i++) {
                        order.text((String) playersSort.get(i));
                        actualPlayer = (String) playersSort.get(0);
                        player.setText("Turn: " + actualPlayer);

                    }

                    order.button("Start game", true);
                    order.show(stage);
                }
                countTurns++;
                if(countTurns <= players.size()){
                    actualPlayer = (String) players.get(countTurns-1);
                    player.setText("Turn: "+actualPlayer);
                }
            }
        });

        if(countTurns == 0){
            Dialog start = new Dialog("Start game", skin, "dialog") {
                public void result(Object obj) {
                    actualPlayer = (String) players.get(0);
                    player.setText("Turn: "+actualPlayer);
                }
            };
            start.text("Every player should roll the dices, the player with the highest number start the game.");
            start.button("Ok", true);
            start.show(stage);
            countTurns++;
        }

    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void update(float delta) {

    }

    public void loadAssets(){
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        bgPixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(Color.rgba8888(20/255f,163/255f,18/255f,255/255f));
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        //load images
        board = new Image(Assets.boardTexture);
        car = new Image(Assets.carTexturebg);
        ship = new Image(Assets.shipTexturebg);
        hat = new Image(Assets.hatTexturebg);
        dog = new Image(Assets.dogTexturebg);
        dadoActual1 = new Image(Assets.dado1);
        dadoActual2 = new Image(Assets.dado1);

        style = new Label.LabelStyle(new BitmapFont(),Color.WHITE);
    }


    public void selectDiceImages(){
        switch((int) obtenerDados(1)){
            case 1:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado1)));
                break;
            case 2:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado2)));
                break;
            case 3:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado3)));
                break;
            case 4:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado4)));
                break;
            case 5:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado5)));
                break;
            case 6:
                dadoActual1.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado6)));
                break;
        }

        switch((int) obtenerDados(2)){
            case 1:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado1)));
                break;
            case 2:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado2)));
                break;
            case 3:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado3)));
                break;
            case 4:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado4)));
                break;
            case 5:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado5)));
                break;
            case 6:
                dadoActual2.setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.dado6)));
                break;
        }
    }

    public void girarDados() {
        dadoS1 = Math.floor(Math.random() * 6) + 1;
        dadoS2 = Math.floor(Math.random() * 6) + 1;
    }

    public double obtenerDados(int dado) {
        if (dado == 1) {
            return dadoS1;
        } else {
            return dadoS2;
        }
    }

    public void resetDices(){
        dadoS1 = 1;
        dadoS2 = 1;
    }

    public void startGame(){
        players.clear();
        System.out.println(playersSort.size());
        for(int i=0;i<playersSort.size();i++){
            String name = String.valueOf(playersSort.get(i));
            players.add(new Player(name, i+1));
        }
        Board board = new Board(players);

    }


    public static void alerta(String alerta) {
        JOptionPane.showMessageDialog(null, alerta);
        Dialog alert = new Dialog("Alert", skin, "dialog") {
            public void result(Object obj) {
            }
        };
        alert.text(alerta);
        alert.button("Ok", true);
        alert.show(stage);
    }





}
