package com.monopoly.game;

import com.monopoly.game.Pantallas.GameScreen;

import javax.swing.JOptionPane;

public class Player {
    public String nombre, color;
    public int posicion, dinero, acreedor, turnosCarcel, communityChestIndex = 0, chanceIndex = 0, id;
    public boolean carcel, chanceCarcel, communityCarcel, ofertas, humano;

    //Constructor de los jugadores
    public Player(String nombre, int id) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = 0;
        this.dinero = 1500;
        this.acreedor = -1;
        this.carcel = false;
        this.turnosCarcel = 0;
        this.communityCarcel = false;
        this.chanceCarcel = false;
    }


    public boolean pagar(int cantidad, int acreedor) {
        if (cantidad <= this.dinero) {
            this.dinero = dinero - cantidad;
            //Crear un método para actualizar el dinero por pantalla o consola.
            return true;
        } else {
            this.dinero = dinero - cantidad;
            this.acreedor = acreedor;
            //Crear un método para actualizar el dinero por pantalla o consola.
            return false;
        }
    }

    public void añadirCantidad(int cantidad, String causa) {
        this.dinero = dinero + cantidad;
        alerta(this.nombre + " recibe $" + cantidad + " de " + causa + ".");
    }

    public void restarCantidad(int cantidad, String causa) {
        this.pagar(cantidad, 0);
        alerta(this.nombre + " pierde $" + cantidad + " de " + causa + ".");
    }

    public void veCarcel() {
        this.carcel = true;
        setPosicion(10);
        alerta(this.nombre + " fue envíado a la cárcel.");
    }

    public void setPosicion(int posicion){
        this.posicion = posicion;
    }

    public void usarCartaCarcel(int deck1[], int deck2[], int indice1, int indice2) {
        this.carcel = false;
        this.turnosCarcel = 0;
        this.posicion = 10;
        if (this.communityCarcel) {
            this.communityCarcel = false;
            for (int i = 0; i < deck1.length; i++) {
                if (deck1[i] == 20) {
                    deck1[i] = 0;
                }
            }
        }else if (this.chanceCarcel){
            this.chanceCarcel = false;
            for (int i = 0; i < deck2.length; i++) {
                if (deck2[i] == 20) {
                    deck2[i] = 0;
                }
            }
        }
        alerta(this.nombre + " 'usó la carta 'salir de la cárcel gratis' ");
    }

    public void cartaCarcel(int eleccion, int turno, int deck[], int index) {
        if (eleccion == 1) {
            this.communityCarcel = true;
        } else {
            this.chanceCarcel = true;
        }
        deck[index] = 20;
    }


    public void alerta(String alerta) {
        GameScreen.alerta(alerta, "Alerta de juego");
    }


    public void actualizarDinero(int pContador) {
        Player p_i = this;
        for (int i = 0; i < pContador; i++) {
            JOptionPane.showMessageDialog(null, p_i.nombre + " tiene $" + p_i.dinero + " actualmente");
        }
        if (p_i.dinero < 0) {
            JOptionPane.showMessageDialog(null, p_i.nombre + " está en bancarrota, buena suerte la próxima vez");
        }
    }
}
