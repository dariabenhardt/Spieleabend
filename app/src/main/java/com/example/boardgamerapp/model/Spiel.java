package com.example.boardgamerapp.model;

public class Spiel {
    private int id;
    private String name;
    private int vorschlagVon;

    public Spiel (int id, String name, int vorschlagVon) {
        this.id = id;
        this.name = name;
        this.vorschlagVon = vorschlagVon;
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

    public int getVorschlagVon() {
        return vorschlagVon;
    }

    public void setVorschlagVon(int vorschlagVon) {
        this.vorschlagVon = vorschlagVon;
    }
}
