package com.example.boardgamerapp.model;

public class Spieler {
    private int id;
    private String name;
    private int turnusPosition;

    public Spieler (int id, String name, int turnusPosition) {
        this.id = id;
        this.name = name;
        this.turnusPosition = turnusPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurnusPosition() {
        return turnusPosition;
    }

    public void setTurnusPosition(int turnusPosition) {
        this.turnusPosition = turnusPosition;
    }
}
