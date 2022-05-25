
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
import com.monopoly.game.Property;
import com.monopoly.game.Screens;
import com.monopoly.game.monopoly;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen extends Screens {
    static Skin skin;
    public Pixmap bgPixmap;
    public TextureRegionDrawable textureRegionDrawableBg;
    public Image boardImage, car, ship,
            hat, dog, dadoActual1, dadoActual2;
    public Label.LabelStyle style;
    public int cantPlayers;
    public double dadoS1 = 1, dadoS2 = 1;

    public List players = new ArrayList();
    public List playersSort = new ArrayList();
    public List counters = new ArrayList();
    ArrayList<Image> playersSkin = new ArrayList<Image>();

    public Boolean start = false;
    public int countTurns = 0;
    public String actualPlayer = "";
    public int actualMoney;
    public Label player, money;
    public Board board;
    public Table menu;
    public Image propertyCard = new Image();
    public boolean draw = false;
    TextButton comprar,Roll, End;

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

        money = new Label("Money: ",skin);
        money.setAlignment(Align.center);

        Label turn = new Label("Players: ",skin);
        turn.setAlignment(Align.left);

        Roll = new TextButton("Roll Dice", skin);
        End = new TextButton("End Turn", skin);
        End.setVisible(false);
        TextButton Monopoly = new TextButton("Monopoly", skin);

        Table dados = new Table();
        dados.pad(5f);
        dados.add(player).colspan(2).fillX();
        dados.row();
        dados.add(dadoActual1);
        dados.add(dadoActual2).padLeft(10f);

        menu = new Table();
        menu.add(Roll).colspan(2).fillX().size(120,50);
        menu.row();
        menu.add(End).colspan(2).fillX().padTop(20F).size(120,50);
        menu.row();
        menu.add(money).colspan(2).fillX().padTop(20F).size(120,50);



        Table turns = new Table();
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
        top.add(turns).fillX();
        top.add(Monopoly).size(150,100).expand().align(Align.right).padRight(20);

        Table first_table = new Table();
        first_table.defaults().pad(5F);
        first_table.setBackground(textureRegionDrawableBg);
        first_table.defaults().pad(5F);
        first_table.add(dados).expand().fillX();
        first_table.row();
        first_table.add(menu).expand().height(Gdx.graphics.getHeight()*3/4).fillX();

        Table second_table= new Table();
        second_table.defaults().pad(5F);
        second_table.setBackground(textureRegionDrawableBg);
        second_table.add(top).fillX();
        second_table.row();
        second_table.add(boardImage).expand().fillY();


        table.add(first_table).width(Gdx.graphics.getWidth()/4).fillY();
        table.add(second_table).expand().fillY();



        stage.addActor(table);


        Roll.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                propertyCard.setVisible(true);
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

                if(start == true){
                    int movimiento = (int) (dadoS1 + dadoS2);
                    if((board.CurrentPlayer.posicion + movimiento)>39){
                        if((board.CurrentPlayer.posicion + movimiento)==40){
                            board.CurrentPlayer.posicion = 0;
                        }else{
                            board.CurrentPlayer.posicion = (board.CurrentPlayer.posicion + movimiento) - 40;
                        }

                    }else {
                        board.CurrentPlayer.posicion = board.CurrentPlayer.posicion + movimiento;
                        board.CheckSquare();
                    }


                    comprar = new TextButton("Comprar", skin);
                    if(board.CurrentPosition.data instanceof Property && draw == false){
                        menu.row();
                        menu.add(propertyCard).colspan(2).fillX().padTop(20F).size(219,250);
                        menu.row();
                        menu.add(comprar).width(100).padTop(20F).colspan(2).fillX();
                        draw = true;
                    }
                    if(board.CurrentPosition.data instanceof Property){

                        if(((Property)board.CurrentPosition.data).idOwner == 0){
                            comprar.setVisible(true);
                            comprar.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    ((Property)board.CurrentPosition.data).idOwner = board.CurrentPlayer.id;
                                    ((Player)board.CurrentTurn.data).dinero = ((Player)board.CurrentTurn.data).dinero - ((Property)board.CurrentPosition.data).price;
                                    board.actPlayer();
                                    board.actSquare();
                                    actualMoney = board.CurrentPlayer.dinero;
                                    money.setText("Money: " + actualMoney);
                                }
                            });
                        }else{
                            comprar.setVisible(false);
                        }

                        propertyCard.setDrawable(((Property)board.CurrentPosition.data).image.getDrawable());
                    }
                    Roll.setVisible(false);
                    menu.getCell(Roll).size(0,0);
                    End.setVisible(true);
                    menu.getCell(End).size(120,50);
                }

            }
        });

        End.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                board.nextTurn();
                actualPlayer = board.CurrentPlayer.nombre;
                player.setText("Turn: " + actualPlayer);
                actualMoney = board.CurrentPlayer.dinero;
                money.setText("Money: " + actualMoney);
                if(board.CurrentPosition.data instanceof Property){
                    propertyCard.setVisible(true);
                    propertyCard.setDrawable(((Property)board.CurrentPosition.data).image.getDrawable());
                }else{
                    propertyCard.setVisible(false);
                }
                board.printActualMoney();
                comprar.setVisible(false);
                Roll.setVisible(true);
                menu.getCell(Roll).size(120,50);
                End.setVisible(false);
                menu.getCell(End).size(0,0);
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
        if(players.contains("dog")){
            spriteBatch.draw(dogS.getTexture(),Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f),Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f),40,40 );
        }
        if(players.contains("ship")){
            spriteBatch.draw(shipS.getTexture(),Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f),Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f),70,50 );
        }
        if(players.contains("car")){
            spriteBatch.draw(carS.getTexture(),Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f),Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f),60,45 );
        }
        if(players.contains("hat")){
            spriteBatch.draw(hatS.getTexture(),Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f),Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f),40,40 );
        }
    }

    @Override
    public void update(float delta) {

    }

    public void loadAssets(){
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        bgPixmap = new Pixmap(1,1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(Color.rgba8888(55/255f,54/255f,46/255f,0.5f));
        bgPixmap.fill();
        textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        //load images
        boardImage = new Image(Assets.boardTexture);
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
        for(int i=0;i<playersSort.size();i++){
            String name = String.valueOf(playersSort.get(i));
            players.add(new Player(name, i+1));
        }
        board = new Board(players);
        actualPlayer = board.CurrentPlayer.nombre;
        player.setText("Turn: " + actualPlayer);
        actualMoney = board.CurrentPlayer.dinero;
        money.setText("Money: " + actualMoney);
        board.printActualMoney();
        start = true;
    }

    public static void alerta(String alerta, String label) {
        Dialog alert = new Dialog(label, skin, "dialog") {
            public void result(Object obj) {
            }
        };
        alert.text(alerta);
        alert.button("Ok", true);
        alert.show(stage);
    }



}
