package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.monopoly.game.Pantallas.GameScreen;
import com.monopoly.game.utils.CircularList;
import com.monopoly.game.utils.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Board {
    public CircularList Players = new CircularList();
    public CircularList Squares = new CircularList();
    public CircularList ComunityCards = new CircularList();
    public CircularList ChanceCards = new CircularList();

    public Player CurrentPlayer;
    public Node CurrentTurn;


    public Board(List Players){
        for(int j = 0; j<Players.size(); j++){
            this.Players.add(Players.get(j));
        }
        loadSquares();
        loadCards();
        CurrentTurn = this.Players.head;
        CurrentPlayer = (Player) CurrentTurn.data;
    }

    public void loadSquares(){
        this.Squares.add(new Corners("Go", 0));
        this.Squares.add(new Property("Mediterranean Avenue", 1, "Bank", 0, 60, 2));
        this.Squares.add(new Fortune("Community Chest", 2, 1));
        this.Squares.add(new Property("Baltic Avenue", 3, "Bank", 0, 60, 4));
        this.Squares.add(new Tax("City Tax", 4, 200));
        this.Squares.add(new Property("Reading Railroad", 5, "Bank", 0, 200, 0));
        this.Squares.add(new Property("Oriental Avenue", 6, "Bank", 0, 100, 6));
        this.Squares.add(new Fortune("Chance", 7, 2));
        this.Squares.add(new Property("Vermont Avenue", 8, "Bank", 0, 100, 6));
        this.Squares.add(new Property("Connecticut Avenue", 9, "Bank", 0, 120, 8));
        this.Squares.add(new Corners("Just Visiting", 10));
        this.Squares.add(new Property("St. Charles Place", 11, "Bank", 0, 140, 10));
        this.Squares.add(new Property("Electric Company", 12, "Bank", 0, 150, 0));
        this.Squares.add(new Property("States Avenue", 13, "Bank", 0, 140, 10));
        this.Squares.add(new Property("Virginia Avenue", 14, "Bank", 0, 160, 12));
        this.Squares.add(new Property("Pennsylvania Railroad", 15, "Bank", 0, 200, 0));
        this.Squares.add(new Property("St. James Place", 16, "Bank", 0, 180, 14));
        this.Squares.add(new Fortune("Community Chest", 17, 1));
        this.Squares.add(new Property("Tennessee Avenue", 18, "Bank", 0, 180, 14));
        this.Squares.add(new Property("New York Avenue", 19, "Bank", 0, 200, 16));
        this.Squares.add(new Corners("Free Parking", 20));
        this.Squares.add(new Property("Kentucky Avenue", 21, "Bank", 0, 220, 18));
        this.Squares.add(new Fortune("Chance", 22, 2));
        this.Squares.add(new Property("Indiana Avenue", 23, "Bank", 0, 220, 18));
        this.Squares.add(new Property("Illinois Avenue", 24, "Bank", 0, 240, 20));
        this.Squares.add(new Property("B&O Railroad", 25, "Bank", 0, 200, 0));
        this.Squares.add(new Property("Atlantic Avenue", 26, "Bank", 0, 260, 22));
        this.Squares.add(new Property("Ventnor Avenue", 27, "Bank", 0, 260, 22));
        this.Squares.add(new Property("Water Works", 28, "Bank", 0, 150, 0));
        this.Squares.add(new Property("Marvin Gardens", 29, "Bank", 0, 280, 24));
        this.Squares.add(new Corners("Go to Jail", 30));
        this.Squares.add(new Property("Pacific Avenue", 31, "Bank", 0, 300, 26));
        this.Squares.add(new Property("North Carolina Avenue", 32, "Bank", 0, 300, 26));
        this.Squares.add(new Fortune("Community Chest", 33, 1));
        this.Squares.add(new Property("Pennsylvania Avenue", 34, "Bank", 0, 320, 28));
        this.Squares.add(new Property("Short Line", 35, "Bank", 0, 200, 0));
        this.Squares.add(new Fortune("Chance", 36, 2));
        this.Squares.add(new Property("Park Place", 37, "Bank", 0, 350, 35));
        this.Squares.add(new Tax("Luxury Tax", 38, 100));
        this.Squares.add(new Property("Boardwalk", 39, "Bank", 0, 400, 50));
    }

    public void loadCards() {
        try {
            FileReader fr = new FileReader("/files/Cards.txt");
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            while ((cadena = br.readLine()) != null) {
                String[] attributes = cadena.split(",");
                int indice = Integer.parseInt(attributes[0]);
                String texto = attributes[1];
                int tipo = Integer.parseInt(attributes[2]);
                if (tipo == 1) {
                    this.ComunityCards.add(new Card(indice, texto, tipo));
                } else {
                    this.ChanceCards.add(new Card(indice, texto, tipo));
                }
            }
        } catch (Exception ex) {
        }
    }


    public void nextTurn(){
        CurrentTurn = CurrentTurn.next;
        CurrentPlayer = (Player) CurrentTurn.data;
    }


    public void CheckSquare() {
        compra = False;
        pos = CurrentPlayer.posicion;
        q_square = Players.head;
        p_square = Squares.head;
        sw = 0;
        sw_property = 0;
        do {
            if (pos == p_square.data.squareId) {
                sw == 1;
            }
            if (p_square.data.price != null) {
                //check if the currentplayer landed on a property
                sw_property = 1;
                {
                    p_square = p_square.next
                } while (p_square != Squares.head || (sw == 1 && sw_property == 1))
                    if (sw == 1 && sw_property == 1) {
                        //check if the bank owns the properry (does the property have owner?)
                        if (p_square.data.ownerName == "Bank") {
                            //code to ask the payer if they wants to buy the property
                            if (compra == True) {
                                //takes away the money from the player if they buy the property
                                CurrentPlayer.dinero = CurrentPlayer.dinero - p_square.data.price
                            }
                            //check if the current player owns the property
                        } else if (p_square.data.ownerName != "Bank" && p_square.data.ownerName != CurrentPlayer.nombre) {
                            //if the currentplayer doesnt own the property and is on it they have to pay rent
                            CurrentPlayer.dinero = CurrentPlayer.dinero - p_square.data.rent
                            //finds the player that owns the property on the list and gives them the rent money paid by currentplayer
                            do {
                                if (q_square.data.nombre == p_square.data.ownerName) {
                                    q_square.data.dinero = q_square.data.dinero + p_square.data.rent
                                }
                                q_square = q_square.next
                            } while (p_square != Squares.head)
                        }
                    }
            }
        }
    }


}
