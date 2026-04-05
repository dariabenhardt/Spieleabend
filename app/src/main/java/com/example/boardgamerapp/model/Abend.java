package com.example.boardgamerapp.model;

public class Abend {
    int id;
    String zeit;
    String datum;
    int gastgeberId;

    public Abend(int id, String zeit, String date, int gastgeberId) {
        this.id = id;
        this.zeit = zeit;
        this.datum = datum;
        this.gastgeberId = gastgeberId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getGastgeberId() {
        return gastgeberId;
    }

    public void setGastgeberId(int gastgeberId) {
        this.gastgeberId = gastgeberId;
    }
}
