package com.monopoly.game.utils;

import com.monopoly.game.Card;
import com.monopoly.game.Player;
import com.monopoly.game.Property;
import com.monopoly.game.Square;

public class CircularList {
    public Node head;
    public Node tail;
    public Node search;
    public CircularList(){
        head = null;
        tail = null;
        search = null;
    }
    public boolean vacia(){
        return head == null;
    }

    public void add(Object data){
        Node node = new Node(data);
        if(vacia()){
            head = node;
            tail = node;
            tail.setNext(head);
        }else{
            tail.setNext(node);
            node.setNext(head);
            tail = node;
        }
    }
    public void imprimir(){
        if (!vacia()) {
            Node reco = head;
            do {
                System.out.print(reco.data + "-");
                reco = reco.next;
            } while (reco!=head);
            System.out.println();
        }
    }

    public Node searchPlayer(int id){
        search = head;
        while (((Player)search.data).id != id){
            search = search.next;
        }
        return search;
    }

    public int cantidad ()
    {
        int cant = 0;
        if (!vacia()) {
            Node reco=head;
            do {
                cant++;
                reco = reco.next;
            } while (reco!=head);
        }
        return cant;
    }

    public void removerPorPosicion(int posicion, int tamanio) {
        if (posicion >= 0 && posicion < tamanio) {
            if (posicion == 0) {
                head = head.next;
            }
            else {
                Node aux = head;
                for (int i = 0; i < posicion - 1; i++) {
                    aux = aux.next;
                }
                Node next = aux.next;
                aux.next = next.next;
            }
            tamanio--;
        }
    }

    public boolean buscar(int referencia){
        // Crea una copia de la lista.
        Node aux = head;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta
        // llegar al final de la lista.
        while(aux != null && encontrado != true){
            // Consulta si el valor del nodo es igual al de referencia.
            if (referencia == ((Property)aux.data).idOwner){
                // Cambia el valor de la bandera.
                encontrado = true;
            }
            else{
                // Avanza al siguiente. nodo.
                aux = aux.next;
            }
        }
        // Retorna el resultado de la bandera.
        return encontrado;
    }

    public Card buscarCarta(int referencia){
        // Crea una copia de la lista.
        Node aux = head;
        // Bandera para indicar si el valor existe.
        boolean encontrado = false;
        // Recorre la lista hasta encontrar el elemento o hasta
        // llegar al final de la lista.
        while(aux != null && encontrado != true){
            // Consulta si el valor del nodo es igual al de referencia.
            if (referencia == ((Card)aux.data).id){
                // Cambia el valor de la bandera.
                encontrado = true;
            }
            else{
                // Avanza al siguiente. nodo.
                aux = aux.next;
            }
        }
        // Retorna el resultado de la bandera.
        return ((Card)aux.data);
    }

    public void editarPorReferencia(int referencia, int valor){
        // Consulta si el valor existe en la lista.
        if (buscar(referencia)) {
            // Crea ua copia de la lista.
            Node aux = head;
            // Recorre la lista hasta llegar al nodo de referencia.
            while(((Property)aux.data).idOwner != referencia){
                aux = aux.next;
            }
            // Actualizamos el valor del nodo
            aux.data = valor;
        }
    }


}


