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
public interface AbendBewertungDao {
    @Insert
    void insert(AbendBewertung bewertung);

    @Update
    void update(AbendBewertung bewertung);

    @Query("SELECT * FROM abend_bewertung WHERE abendId = :abendId")
    LiveData<List<AbendBewertung>> getByAbend(int abendId);

    @Query("SELECT AVG(essenSterne) FROM abend_bewertung WHERE abendId = :abendId")
    float getDurchschnittEssen(int abendId);

    @Query("SELECT AVG(abendSterne) FROM abend_bewertung WHERE abendId = :abendId")
    float getDurchschnittAbend(int abendId);

    @Query("SELECT AVG(gastgeberSterne) FROM abend_bewertung WHERE abendId = :abendId")
    float getDurchschnittGastgeber(int abendId);
}
