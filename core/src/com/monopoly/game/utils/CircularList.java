package com.monopoly.game.utils;

public class CircularList {
    public Node head;
    public Node tail;
    public CircularList(){
        head = null;
        tail = null;
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
                System.out.print (reco.data + "-");
                reco = reco.next;
            } while (reco!=head);
            System.out.println();
        }
    }
}


