package com.example.boardgamerapp.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "spiel_voting",
foreignKeys = {
        @ForeignKey(entity = Spieler.class, parentColumns = "id", childColumns = "spielerId"),
        @ForeignKey(entity = Spiel.class, parentColumns = "id", childColumns = "spielId"),
        @ForeignKey(entity = Abend.class, parentColumns = "id", childColumns = "abendId")
},
        indices = {
                @Index("spielerId"),
                @Index("spielId"),
                @Index("abendId")
        }
)

public class SpielVoting {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int spielerId;
    private int spielId;
    private int abendId;

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

    public SpielVoting(int spielerId, int spielId, int abendId) {
        this.spielerId = spielerId;
        this.spielId = spielId;
        this.abendId = abendId;


    }
}
