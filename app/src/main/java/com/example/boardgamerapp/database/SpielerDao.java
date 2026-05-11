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
public interface SpielerDao {
    @Insert
    long insert (Spieler spieler);

    @Update
    void update(Spieler spieler);

    @Delete
    void delete (Spieler spieler);

    @Query("SELECT * FROM spieler ORDER BY turnusPosition ASC")
    List<Spieler> getAll();

    @Query("SELECT * FROM spieler WHERE id = :id")
    Spieler getById(int id);
}
