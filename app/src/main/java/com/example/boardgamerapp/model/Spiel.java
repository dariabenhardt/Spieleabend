package com.example.boardgamerapp.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "spiel",
foreignKeys = @ForeignKey(
        entity = Spieler.class,
        parentColumns = "id",
        childColumns = "vorschlagVon",
        onDelete = ForeignKey.SET_NULL
),
        indices = {@Index("vorschlagVon")})
public class Spiel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int vorschlagVon;

    public Spiel (String name, int vorschlagVon) {
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
