package com.example.boardgamerapp.model;

public class AbendBewertung {
    int id;
    int abendId;
    int spielerId;
    int essenSterne;
    int abendSterne;
    int gastgeberSterne;

    public AbendBewertung(int id, int abendId, int spielerId) {
        this.id = id;
        this.abendId = abendId;
        this.spielerId = spielerId;
    }

    public AbendBewertung(int id, int abendId, int spielerId, int essenSterne, int abendSterne, int gastgeberSterne) {
        this.id = id;
        this.abendId = abendId;
        this.spielerId = spielerId;
        this.essenSterne = essenSterne;
        this.abendSterne = abendSterne;
        this.gastgeberSterne = gastgeberSterne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbendId() {
        return abendId;
    }

    public void setAbendId(int abendId) {
        this.abendId = abendId;
    }

    public int getSpielerId() {
        return spielerId;
    }

    public void setSpielerId(int spielerId) {
        this.spielerId = spielerId;
    }

    public int getEssenSterne() {
        return essenSterne;
    }

    public void setEssenSterne(int essenSterne) {
        this.essenSterne = essenSterne;
    }

    public int getAbendSterne() {
        return abendSterne;
    }

    public void setAbendSterne(int abendSterne) {
        this.abendSterne = abendSterne;
    }

    public int getGastgeberSterne() {
        return gastgeberSterne;
    }

    public void setGastgeberSterne(int gastgeberSterne) {
        this.gastgeberSterne = gastgeberSterne;
    }
}

