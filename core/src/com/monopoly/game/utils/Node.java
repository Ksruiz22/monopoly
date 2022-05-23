package com.monopoly.game.utils;

public class Node {
    Object data;
    Node next;
    public Node(Object data){
        this.data = data;
        next = null;
    }

    public void setNext(Node next){
        this.next = next;
    }
}
