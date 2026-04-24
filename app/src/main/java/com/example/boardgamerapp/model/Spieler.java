package com.example.boardgamerapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spieler")
public class Spieler {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private int turnusPosition;

    public Spieler (String name, int turnusPosition) {
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
