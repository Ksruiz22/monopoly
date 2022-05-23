package com.monopoly.game.utils;

public class Node {
    public Object data;
    public Node next;
    public Node(Object data){
        this.data = data;
        next = null;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Object getData(){
        return this.data;
    }
}
