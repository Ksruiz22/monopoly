
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
    public int cantDobles = 0, acumulado = 0;
    TextButton comprar,Roll, End;
    boolean dogb = false, shipb = false, carb = false, hatb = false;


    //Posiciones iniciales
    float dogX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f);
    float dogY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);

    float shipX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f);
    float shipY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);

    float hatX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
    float hatY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);

    float carX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
    float carY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);


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

        final Table dados = new Table();
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
        comprar = new TextButton("Comprar", skin);

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

                if(start == true) {

                    if ((dadoS1 == dadoS2) && (board.CurrentPlayer.carcel != true) && (cantDobles<3)) {
                        alerta("Vuelve a lanzar los dados, sacaste dobles", "Anuncio");
                        cantDobles++;
                    }
                    if (cantDobles == 3) {
                        alerta("Vas a la carcel, sacaste 3 dobles", "Anuncio");
                        ((Player)board.CurrentTurn.data).posicion = 10;
                        ((Player)board.CurrentTurn.data).carcel = true;
                        actualMoney = ((Player)board.CurrentTurn.data).dinero;
                        board.actPlayer();
                        cantDobles = 0;
                    }

                    int movimiento = (int) (dadoS1 + dadoS2);

                    if(board.CurrentPlayer.carcel != true){
                        moverFicha(board.CurrentPlayer.posicion, board.CurrentPlayer.posicion + movimiento, board.CurrentPlayer.nombre);
                        if((board.CurrentPlayer.posicion + movimiento)>39){
                            if((board.CurrentPlayer.posicion + movimiento)==40){
                                board.CurrentPlayer.posicion = 0;
                            }else{
                                board.CurrentPlayer.posicion = (board.CurrentPlayer.posicion + movimiento) - 40;
                                ((Player)board.CurrentTurn.data).dinero = (((Player)board.CurrentTurn.data).dinero) + 200;
                                alerta("La banca le da 200$ a: "+ ((Player)board.CurrentTurn.data).nombre, "Alerta");
                                actualMoney = ((Player)board.CurrentTurn.data).dinero;
                                money.setText("Money: " + actualMoney);
                            }
                        }else {
                            board.CurrentPlayer.posicion = board.CurrentPlayer.posicion + movimiento;
                        }
                        board.CheckSquare();
                        actualMoney = ((Player)board.CurrentTurn.data).dinero;
                        money.setText("Money: " + actualMoney);
                        }else{
                        if(dadoS1 == dadoS2){
                            ((Player)board.CurrentTurn.data).carcel = false;
                            ((Player)board.CurrentTurn.data).posicion = ((Player)board.CurrentTurn.data).posicion+ movimiento;
                            board.actPlayer();
                            board.CheckSquare();
                            actualMoney = ((Player)board.CurrentTurn.data).dinero;
                            money.setText("Money: " + actualMoney);
                        }else{
                            ((Player)board.CurrentTurn.data).turnosCarcel ++;
                            if(((Player)board.CurrentTurn.data).turnosCarcel == 3){
                                ((Player)board.CurrentTurn.data).dinero = (((Player)board.CurrentTurn.data).dinero) - 50;
                                alerta("Llevas 3 turnos en la carcel, pagas 50 para salir.", "Alerta");
                                ((Player)board.CurrentTurn.data).carcel = false;
                                ((Player)board.CurrentTurn.data).turnosCarcel = 0;
                                ((Player)board.CurrentTurn.data).posicion = ((Player)board.CurrentTurn.data).posicion+ movimiento;
                                board.actPlayer();
                                board.CheckSquare();
                                board.checkPlayer(board.CurrentPlayer);
                                actualMoney = ((Player)board.CurrentTurn.data).dinero;
                                money.setText("Money: " + actualMoney);
                            }
                        }
                    }


                    if(board.CurrentPosition.data instanceof Property && draw == false){
                        menu.row();
                        menu.add(propertyCard).colspan(2).fillX().padTop(20F).size(219,250);
                        menu.row();
                        menu.add(comprar).width(100).padTop(20F).colspan(2).fillX();
                        draw = true;
                    }

                    if(board.CurrentPosition.data instanceof Property){
                        propertyCard.setVisible(true);
                        if(((Property)board.CurrentPosition.data).idOwner == 0){
                            comprar.setVisible(true);
                        }else{
                            comprar.setVisible(false);
                        }
                        propertyCard.setDrawable(((Property)board.CurrentPosition.data).image.getDrawable());
                    }else{
                        propertyCard.setVisible(false);
                    }


                    if((dadoS1 != dadoS2)){
                        Roll.setVisible(false);
                        menu.getCell(Roll).size(0,0);
                        End.setVisible(true);
                        menu.getCell(End).size(120,50);
                    }

                }

            }
        });

        comprar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(((Player)board.CurrentTurn.data).dinero > ((Property)board.CurrentPosition.data).price){
                    ((Player)board.CurrentTurn.data).dinero = ((Player)board.CurrentTurn.data).dinero - ((Property)board.CurrentPosition.data).price;
                    ((Property)board.CurrentPosition.data).idOwner = ((Player)board.CurrentTurn.data).id;
                    ((Property)board.CurrentPosition.data).ownerName = ((Player)board.CurrentTurn.data).nombre;

                    actualMoney = ((Player)board.CurrentTurn.data).dinero;
                    money.setText("Money: " + actualMoney);
                }else{
                    alerta("No tienes suficiente dinero para comprar esta propiedad","Mensaje");
                }

            }
        });

        End.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                board.nextTurn();
                cantDobles = 0;
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
            dogb = true;}
        if(players.contains("ship")){
            shipb = true;
        }
        if(players.contains("car")){
            carb = true;
        }
        if(players.contains("hat")){
            hatb = true;
        }
        if (dogb == true){
            spriteBatch.draw(dogS.getTexture(),dogX,dogY,40,40 );
        }
        if (shipb == true){
            spriteBatch.draw(shipS.getTexture(),shipX,shipY,70,50 );
        }
        if(carb == true){
            spriteBatch.draw(carS.getTexture(),carX,carY,60,45 );
        }
        if(hatb == true){
            spriteBatch.draw(hatS.getTexture(),hatX,hatY,40,40 );
        }

    }

    public void moverFicha(int posicionActual, int posicionFinal, String ficha){
        if((posicionActual<10) && (posicionFinal <=10)) {
            int mov = posicionFinal  - posicionActual;
            switch (ficha) {
                case "dog":
                        dogX = ((dogX - (90 * mov)));
                    break;
                case "ship":
                        shipX = ((shipX - (90 * mov)));
                    break;
                case "car":
                        carX = ((carX - (95 * mov)));
                    break;
                case "hat":
                        hatX = ((hatX - (95 * mov)));
                    break;

            }
        }

        if(posicionFinal == 30){
            switch (ficha) {
                case "dog":
                    dogX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f))-980;
                    dogY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);
                    break;
                case "ship":
                    shipX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f))-980;
                    shipY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);
                    break;
                case "car":
                    carX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    carY= Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);
                    break;
                case "hat":
                    hatX = ( Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    hatY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);
                    break;

            }
        }

        if(posicionActual == 9 && posicionFinal == 21){
            switch (ficha) {
                case "dog":
                    dogY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.20f);
                    break;
                case "ship":
                    shipY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.24f);
                    break;
                case "car":
                    carY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.24f);
                    break;
                case "hat":
                    hatY = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() * 0.20f);
                    break;
            }
        }

        if(posicionActual == 19 && posicionFinal == 31){
            switch (ficha) {
                case "dog":
                    dogX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f);
                    break;
                case "ship":
                    shipX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f);
                    break;
                case "car":
                    carX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
                    break;
                case "hat":
                    hatX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
                    break;
            }
        }
        if(posicionActual == 29 && posicionFinal == 41){
            switch (ficha) {
                case "dog":
                    dogY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);
                    break;
                case "ship":
                    shipY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);
                    break;
                case "car":
                    carY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.94f);
                    break;
                case "hat":
                    hatY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.98f);
                    break;
            }
        }
        if(posicionActual == 39 && posicionFinal == 51){
            switch (ficha) {
                case "dog":
                    dogX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f))-980;
                    break;
                case "ship":
                    shipX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f))-980;
                    break;
                case "car":
                    carX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    break;
                case "hat":
                    hatX = ( Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    break;
            }
        }




        if((posicionActual<10) && (posicionFinal >10) && (posicionFinal<=20)) {
            int mov = posicionFinal  - ((10-posicionActual)+posicionActual);
            switch (ficha) {
                case "dog":
                    dogX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f))-980;
                    dogY = ((dogY + (65 * (mov))));
                    break;
                case "ship":
                    shipX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f))-980;
                    shipY = ((shipY + (70 * (mov))));
                    break;
                case "car":
                    carX = (Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    carY = ((carY + (65 * (mov))));
                    break;
                case "hat":
                    hatX = ( Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f))-980;
                    hatY = ((hatY  +(70* (mov))));
                    break;
            }
        }

        if((posicionActual>=10) && (posicionFinal<=20)){
            int mov = posicionFinal - posicionActual;
            switch (ficha){
                case "dog":
                    dogY = ((dogY + (65 * (mov))));
                    break;
                case "ship":
                    shipY = ((shipY + (7 * (mov))));
                    break;
                case "car":
                    carY = ((carY + (65 * (mov))));
                    break;
                case "hat":
                    hatY = ((hatY  +(7 * (mov))));
                    break;
            }

        }


        if((posicionActual>=20) && (posicionFinal <30)) {
            int mov = posicionFinal-20;
            switch (ficha) {
                case "dog":
                    dogX = ((dogX + (50 * mov)));
                    break;
                case "ship":
                    shipX = ((shipX + (50 * mov)));
                    break;
                case "car":
                    carX = ((carX + (50 * mov)));
                    break;
                case "hat":
                    hatX = ((hatX + (50 * mov)));
                    break;
            }
        }


        if((posicionActual>=10) && (posicionFinal>20) && (posicionFinal<30)) {
            int mov = posicionFinal-20;
            switch (ficha) {
                case "dog":
                    dogX = ((dogX + (90 * mov)));
                    dogY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.20f);
                    break;
                case "ship":
                    shipX = ((shipX + (90 * mov)));
                    shipY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.24f);
                    break;
                case "car":
                    carX = ((carX + (90 * mov)));
                    carY= Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.24f);
                    break;
                case "hat":
                    hatX = ((hatX + (90 * mov)));
                    hatY = Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()*0.20f);
                    break;

            }
        }

        if((posicionActual>=20) && (posicionFinal>30) && (posicionFinal<40)) {
            int mov = posicionFinal-30;
            switch (ficha) {
                case "dog":
                    dogX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f);
                    dogY = ((dogY - (65 * (mov))));
                    break;
                case "ship":
                    shipX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.11f);
                    shipY = ((shipY - (72 * (mov))));
                    break;
                case "car":
                    carX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
                    carY = ((carY - (65 * (mov))));
                    break;
                case "hat":
                    hatX = Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.08f);
                    hatY = ((hatY  - (72 * (mov))));
                    break;

            }
        }

        if((posicionActual>=39) && (posicionFinal<=40) ) {
            int mov = posicionFinal-30;
            switch (ficha) {
                case "dog":
                    dogY = ((dogY - (65 * (mov))));
                    break;
                case "ship":
                    shipY = ((shipY - (72 * (mov))));
                    break;
                case "car":
                    carY = ((carY - (65 * (mov))));
                    break;
                case "hat":
                    hatY = ((hatY  - (72 * (mov))));
                    break;

            }
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

    public static void fin() {
        Dialog alert = new Dialog("FIN", skin, "dialog") {
            public void result(Object obj) {
                Gdx.app.exit();
            }
        };
        alert.text("HAS GANADO!!, TODOS LOS DEMÁS JUGADORES ESTAN EN BANCARROTA");
        alert.button("Ok", true);
        alert.show(stage);
    }
}
