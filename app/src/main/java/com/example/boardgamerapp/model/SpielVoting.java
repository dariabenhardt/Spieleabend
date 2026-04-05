package com.example.boardgamerapp.model;

public class SpielVoting {
    int id;
    int spielerId;
    int spielId;
    int abendId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpielerId() {
        return spielerId;
    }

    public void setSpielerId(int spielerId) {
        this.spielerId = spielerId;
    }

    public int getSpielId() {
        return spielId;
    }

    public void setSpielId(int spielId) {
        this.spielId = spielId;
    }

    public int getAbendId() {
        return abendId;
    }

    public void setAbendId(int abendId) {
        this.abendId = abendId;
    }

    public SpielVoting(int id, int spielerId, int spielId, int abendId) {
        this.id = id;
        this.spielerId = spielerId;
        this.spielId = spielId;
        this.abendId = abendId;


    }
}
