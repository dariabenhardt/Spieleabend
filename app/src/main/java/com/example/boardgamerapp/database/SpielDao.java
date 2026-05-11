package com.example.boardgamerapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.boardgamerapp.model.*;

import java.util.List;

@Dao
public interface SpielDao {
    @Insert
    long insert(Spiel spiel);

    @Delete
    void delete(Spiel spiel);

    @Query("SELECT * FROM spiel")
    LiveData<List<Spiel>> getAll();

    @Query("SELECT * FROM spiel WHERE vorschlagVon = :spielerId")
    List<Spiel> getByVorschlaeger(int spielerId);
}
