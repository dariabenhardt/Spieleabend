package com.example.boardgamerapp.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "abend",
foreignKeys = @ForeignKey(
        entity = Spieler.class,
        parentColumns = "id",
        childColumns = "gastgeberId",
        onDelete = ForeignKey.SET_NULL
),
        indices = {@Index("gastgeberId")}
)
public class Abend {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String zeit;
    private String datum;
    private int gastgeberId;

    public Abend(String zeit, String datum, int gastgeberId) {
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
