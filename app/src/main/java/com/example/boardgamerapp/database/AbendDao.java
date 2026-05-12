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
public interface AbendDao {
    @Insert
    long insert(Abend abend);

    @Update
    void update(Abend abend);

    @Delete
    void delete(Abend abend);

    @Query("SELECT * FROM abend ORDER BY datum ASC")
    LiveData<List<Abend>> getAll();

    @Query("SELECT * FROM abend WHERE datum >= :heute ORDER BY datum ASC LIMIT 1")
    LiveData<Abend> getNaechstenAbend(String heute);

    @Query("SELECT * FROM abend WHERE id = :id")
    LiveData<Abend> getById(int id);

    @Query("SELECT * FROM abend ORDER BY datum DESC LIMIT 1")
    Abend getLetzten();

}
