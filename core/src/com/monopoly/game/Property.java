package com.monopoly.game;


public class Property extends Square {
    String ownerName;
    int idOwner, price, rent;

    public Property(String name, int squareId, String ownerName, int idOwner, int price, int rent) {
        super(name, squareId);
        this.ownerName = ownerName;
        this.idOwner = idOwner;
        this.price = price;
        this.rent = rent;
    }

    public int getOwnerId(){
        return this.idOwner;
    }

    public String getOwnerName(){
        return this.ownerName;
    }

    public int getRent(){
        return this.rent;
    }

    public int getPrice() {
        return price;
    }

}
