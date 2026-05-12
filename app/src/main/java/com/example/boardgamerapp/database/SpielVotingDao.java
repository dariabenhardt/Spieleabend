
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
public interface SpielVotingDao {
    @Insert
    void insert(SpielVoting voting);

    @Query("SELECT * FROM spiel_voting WHERE abendId = :abendId")
    LiveData<List<SpielVoting>> getByAbend(int abendId);

    @Query("SELECT * FROM spiel_voting WHERE abendId = :abendId")
    List<SpielVoting> getByAbendSync(int abendId);

    // Gewinner: Spiel mit meisten Votes
    @Query("SELECT spielId FROM spiel_voting WHERE abendId = :abendId " +
            "GROUP BY spielId ORDER BY COUNT(*) DESC LIMIT 1")
    int getGewinnerSpielId(int abendId);
}
