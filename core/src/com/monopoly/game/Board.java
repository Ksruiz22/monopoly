package com.monopoly.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.monopoly.game.Pantallas.GameScreen;
import com.monopoly.game.utils.CircularList;
import com.monopoly.game.utils.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Random;

public class Board {
    public CircularList Players = new CircularList();
    public CircularList Squares = new CircularList();
    public CircularList ComunityCards = new CircularList();
    public CircularList ChanceCards = new CircularList();

    public Player CurrentPlayer;
    public Node CurrentTurn;

    public Square CurrentSquare;
    public Node CurrentPosition;

    int CantPlayers;
    public boolean cardJail = false;
    public boolean cardJail2 = false;

    public Board(List Players){
        CantPlayers = Players.size();
        for(int j = 0; j<Players.size(); j++){
            this.Players.add(Players.get(j));
        }
        loadSquares();
        loadCards();
        ComunityCards.imprimir();
        ChanceCards.imprimir();
        CurrentTurn = this.Players.head;
        CurrentPlayer = (Player) CurrentTurn.data;

        CurrentPosition = this.Squares.head;
        CurrentSquare = (Square) CurrentPosition.data;
    }

    public void loadSquares(){
        this.Squares.add(new Corners("Go", 0));
        this.Squares.add(new Property("Mediterranean Avenue", 1, "Bank", 0, 60, 2, new Image(Assets.cp1)));
        this.Squares.add(new Fortune("Community Chest", 2, 1));
        this.Squares.add(new Property("Baltic Avenue", 3, "Bank", 0, 60, 4, new Image(Assets.cp3)));
        this.Squares.add(new Tax("City Tax", 4, 200));
        this.Squares.add(new Property("Reading Railroad", 5, "Bank", 0, 200, 0, new Image(Assets.cp5)));
        this.Squares.add(new Property("Oriental Avenue", 6, "Bank", 0, 100, 6, new Image(Assets.cp6)));
        this.Squares.add(new Fortune("Chance", 7, 2));
        this.Squares.add(new Property("Vermont Avenue", 8, "Bank", 0, 100, 6, new Image(Assets.cp8)));
        this.Squares.add(new Property("Connecticut Avenue", 9, "Bank", 0, 120, 8, new Image(Assets.cp9)));
        this.Squares.add(new Corners("Just Visiting", 10));
        this.Squares.add(new Property("St. Charles Place", 11, "Bank", 0, 140, 10, new Image(Assets.cp11)));
        this.Squares.add(new Property("Electric Company", 12, "Bank", 0, 150, 0, new Image(Assets.cp12)));
        this.Squares.add(new Property("States Avenue", 13, "Bank", 0, 140, 10, new Image(Assets.cp13)));
        this.Squares.add(new Property("Virginia Avenue", 14, "Bank", 0, 160, 12, new Image(Assets.cp14)));
        this.Squares.add(new Property("Short Line", 15, "Bank", 0, 200, 0, new Image(Assets.cp35)));
        this.Squares.add(new Property("St. James Place", 16, "Bank", 0, 180, 14, new Image(Assets.cp16)));
        this.Squares.add(new Fortune("Community Chest", 17, 1));
        this.Squares.add(new Property("Tennessee Avenue", 18, "Bank", 0, 180, 14, new Image(Assets.cp18)));
        this.Squares.add(new Property("New York Avenue", 19, "Bank", 0, 200, 16, new Image(Assets.cp19)));
        this.Squares.add(new Corners("Free Parking", 20));
        this.Squares.add(new Property("Kentucky Avenue", 21, "Bank", 0, 220, 18, new Image(Assets.cp21)));
        this.Squares.add(new Fortune("Chance", 22, 2));
        this.Squares.add(new Property("Indiana Avenue", 23, "Bank", 0, 220, 18, new Image(Assets.cp23)));
        this.Squares.add(new Property("Illinois Avenue", 24, "Bank", 0, 240, 20, new Image(Assets.cp24)));
        this.Squares.add(new Property("B&O Railroad", 25, "Bank", 0, 200, 0, new Image(Assets.cp25)));
        this.Squares.add(new Property("Atlantic Avenue", 26, "Bank", 0, 260, 22, new Image(Assets.cp26)));
        this.Squares.add(new Property("Ventnor Avenue", 27, "Bank", 0, 260, 22, new Image(Assets.cp27)));
        this.Squares.add(new Property("Internet Services Provider", 28, "Bank", 0, 150, 0, new Image(Assets.cp38)));
        this.Squares.add(new Property("Marvin Gardens", 29, "Bank", 0, 280, 24, new Image(Assets.cp29)));
        this.Squares.add(new Corners("Go to Jail", 30));
        this.Squares.add(new Property("Pacific Avenue", 31, "Bank", 0, 300, 26, new Image(Assets.cp31)));
        this.Squares.add(new Property("North Carolina Avenue", 32, "Bank", 0, 300, 26, new Image(Assets.cp32)));
        this.Squares.add(new Fortune("Community Chest", 33, 1));
        this.Squares.add(new Property("Pennsylvania Avenue", 34, "Bank", 0, 320, 28, new Image(Assets.cp34)));
        this.Squares.add(new Property("Pennsylvania Railroad", 35, "Bank", 0, 200, 0, new Image(Assets.cp15)));
        this.Squares.add(new Fortune("Chance", 36, 2));
        this.Squares.add(new Property("Park Place", 37, "Bank", 0, 350, 35, new Image(Assets.cp37)));
        this.Squares.add(new Property("Water Works", 38, "Bank", 0, 150, 0, new Image(Assets.cp28)));
        this.Squares.add(new Property("Boardwalk", 39, "Bank", 0, 400, 50, new Image(Assets.cp39)));
    }

    public void loadCards() {
        try {
            FileReader fr = new FileReader("C:\\Users\\lu23p\\Desktop\\Estructura de datos\\monopoly\\core\\src\\com\\monopoly\\game\\files\\Cards.txt");
            BufferedReader br = new BufferedReader(fr);
            String cadena = br.readLine();
            int indice, tipo, dinero;
            String texto;
            String[] attributes;
            while (cadena != null) {
                attributes = cadena.split(",");
                indice = Integer.valueOf(attributes[0]);
                texto = attributes[1];
                tipo = Integer.valueOf(attributes[2]);
                dinero = Integer.valueOf(attributes[3]);
                if (tipo == 1) {
                    this.ComunityCards.add(new Card(indice, texto, tipo, dinero));
                } else {
                    this.ChanceCards.add(new Card(indice, texto, tipo, dinero));
                }
                cadena = br.readLine();
            }

        } catch (Exception ex) {
        }
    }


    public void nextTurn(){
        CurrentTurn = CurrentTurn.next;
        CurrentPlayer = (Player) CurrentTurn.data;
        getCurrentSquare(CurrentPlayer);
    }




    public void getCurrentSquare(Player CurrentPlayer) {
        while (CurrentPlayer.posicion != CurrentSquare.squareId) {
            CurrentPosition = CurrentPosition.next;
            CurrentSquare = (Square) CurrentPosition.data;
        }
    }
    public void actPlayer(){
        CurrentPlayer = (Player) CurrentTurn.data;
    }

    public void checkPlayer(Player player){
        if(player.dinero<=0){
            GameScreen.alerta(player.nombre + "ha quedado en bancarrota","Alerta");
            Players.removerPorPosicion(player.id+1, CantPlayers);
            CantPlayers = CantPlayers-1;
            while(Squares.buscar(player.id) == true){
                Squares.editarPorReferencia(player.id, 0);
            }
        }
        if(CantPlayers == 1){
            GameScreen.alerta((((Player)Players.head.data).nombre) + "HA GANADO!!, TODOS LOS DEMÁS JUGADORES ESTAN EN BANCARROTA","Alerta");

        }
    }


    public void CheckSquare() {
        getCurrentSquare(CurrentPlayer);
        if(CurrentPosition.data instanceof Property) {
            if (((Property)CurrentPosition.data).idOwner == 0) {

            }
            if (((Property)CurrentPosition.data).idOwner != 0) {
                if ((((Property)CurrentPosition.data).idOwner) != CurrentPlayer.id) {
                    ((Player)CurrentTurn.data).dinero = (((Player)CurrentTurn.data).dinero)-(((Property)CurrentPosition.data).rent);
                    ((Player)Players.searchPlayer(((Property)CurrentPosition.data).idOwner).data).dinero = ((Player)Players.searchPlayer(((Property)CurrentPosition.data).idOwner).data).dinero + (((Property)CurrentPosition.data).rent);
                    checkPlayer(CurrentPlayer);
                }
            }
        }
        if(CurrentPosition.data instanceof Fortune) {
            if(((Fortune) CurrentPosition.data).type == 1){
                int max = 12;
                Random random = new Random();
                int cardNum = random.nextInt(max);
                System.out.println(cardNum);

                if((this.cardJail = true) && (cardNum == 0)){
                    while (cardNum == 0){
                        cardNum = random.nextInt(max);
                    }
                }
                Card card = ComunityCards.buscarCarta(cardNum);
                System.out.println(card.text);
                if((card.id != 0) && (card.id != 12)){
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).dinero += card.dinero;
                    actPlayer();
                }else if(card.id == 0){
                    this.cardJail = true;
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).communityCarcel = true;
                    actPlayer();
                }else if(card.id == 12){
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).carcel = true;
                    ((Player)CurrentTurn.data).posicion = 10;
                    actPlayer();
                }
            }
            if(((Fortune) CurrentPosition.data).type == 2){
                int max = 5;
                Random random = new Random();
                int cardNum = random.nextInt(max);
                System.out.println(cardNum);

                if((this.cardJail2 = true) && (cardNum == 0)){
                    while (cardNum == 0){
                        cardNum = random.nextInt(max);
                    }
                }

                Card card = ChanceCards.buscarCarta(cardNum);
                System.out.println(card.text);
                if((card.id != 0) && (card.id != 5)){
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).dinero += card.dinero;
                    actPlayer();
                }else if(card.id == 0){
                    this.cardJail2 = true;
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).communityCarcel = true;
                    actPlayer();
                }else if(card.id == 5){
                    GameScreen.alerta(card.text,"Anuncio");
                    ((Player)CurrentTurn.data).carcel = true;
                    ((Player)CurrentTurn.data).posicion = 10;
                    actPlayer();
                }
            }
        }
        if(CurrentPosition.data instanceof Corners){
            if(((Corners)CurrentPosition.data).squareId == 0){
                ((Player)CurrentTurn.data).dinero = (((Player)CurrentTurn.data).dinero) + 200;
                CurrentPlayer = (Player) CurrentTurn.data;
            }
            if(((Corners)CurrentPosition.data).squareId == 10){
                GameScreen.alerta("Estas de visita en la carcel", "Anuncio");
            }
            if(((Corners)CurrentPosition.data).squareId == 20){
                GameScreen.alerta("Estas en el parqueadero", "Anuncio");
            }
            if(((Corners)CurrentPosition.data).squareId == 30){
                ((Player)CurrentTurn.data).posicion = 10;
                GameScreen.alerta(((Player)CurrentTurn.data).nombre +" va a la carcel", "Anuncio");
                ((Player)CurrentTurn.data).carcel = true;
                CurrentPlayer = (Player) CurrentTurn.data;
            }
        }

    }

    public void printActualMoney(){
        if (!Players.vacia()) {
            Node reco = Players.head;
            do {
                System.out.print(((Player)reco.data).posicion + "-");
                System.out.print(((Player)reco.data).nombre + "-");
                System.out.print(((Player)reco.data).dinero + "-");
                reco = reco.next;
            } while (reco!=Players.head);
            System.out.println();
        }
    }


}
