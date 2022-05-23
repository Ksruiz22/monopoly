package com.monopoly.game;

import com.badlogic.gdx.Gdx;
import com.monopoly.game.utils.CircularList;

import java.io.BufferedReader;
import java.io.FileReader;

public class Card {
    public CircularList ComunityCards;
    public CircularList ChanceCards;
    public String text;
    public int type;
    public int id;
    public Card(int id, String text , int type){
        this.text = text;
        this.type = type;
        this.id = id;
    }
    public String getText(){
        return this.text;
    }

    public void giveMoney(Player player, int amount){
        player.añadirCantidad(amount, this.text);
    }

    public void removeMoney(Player player, int amount){

    }

    public void GoToJail(Player player){
        player.veCarcel();
    }

    public void outJail(){

    }

    public void loadCards(int Tipo) {
        try {
            FileReader fr = new FileReader(String.valueOf(Gdx.files.internal("files/Cards.txt")));
            BufferedReader br = new BufferedReader(fr);
            String cadena;
            switch (Tipo) {
                case 1:
                    while ((cadena = br.readLine()) != null) {
                        String[] attributes = cadena.split(",");
                        int indice = Integer.parseInt(attributes[0]);
                        String texto = attributes[1];
                        int tipo = Integer.parseInt(attributes[2]);
                        this.ComunityCards.add(new Card(indice, texto, tipo));
                    }
                case 2:
                    while ((cadena = br.readLine()) != null) {
                        String[] attributes = cadena.split(",");
                        int indice = Integer.parseInt(attributes[0]);
                        String texto = attributes[1];
                        int tipo = Integer.parseInt(attributes[2]);
                        this.ChanceCards.add(new Card(indice, texto, tipo));
                    }
            }
        } catch (Exception ex) {
        }
    }

}
