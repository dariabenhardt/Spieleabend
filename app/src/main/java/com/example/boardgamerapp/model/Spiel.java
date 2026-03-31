package com.example.boardgamerapp.model;

public class Spiel {
    public int id;
    public String name;
    public int vorschlagVon;

    public Spiel (int id, String name, int vorschlagVon) {
        this.id = id;
        this.name = name;
        this.vorschlagVon = vorschlagVon;
    }
}
