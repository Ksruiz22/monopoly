package com.monopoly.game;

public class Corners extends Square {

    public Corners(String name, int squareId) {
        super(name, squareId);

    }
    public void Go(Player player){
        player.añadirCantidad(200, "pasar por el inicio");
    }

    public void VisitJail(Player player){
        player.alerta("Estas de visita en la carcel");
    }

    public void Parking(Player player){
        player.alerta("Estas en el parqueadero");
    }

    public void GoToJail(Player player){
        player.veCarcel();
    }



}


