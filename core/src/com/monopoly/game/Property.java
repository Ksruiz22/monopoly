package com.monopoly.game;


import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Property extends Square {
    public String ownerName;
    public int idOwner, price, rent;
    public Image image;
    public Property(String name, int squareId, String ownerName, int idOwner, int price, int rent, Image image) {
        super(name, squareId);
        this.ownerName = ownerName;
        this.idOwner = idOwner;
        this.price = price;
        this.rent = rent;
        this.image = image;
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
