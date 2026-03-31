package com.example.boardgamerapp.model;

public class Spieler {
    public int id;
    public String name;
    public int turnusPosition;

    public Spieler (int id, String name, int turnusPosition) {
        this.id = id;
        this.name = name;
        this.turnusPosition = turnusPosition;
    }
}
