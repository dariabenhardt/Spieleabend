package com.example.boardgamerapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.boardgamerapp.database.AppDatabase;
import com.example.boardgamerapp.database.SpielVotingDao;
import com.example.boardgamerapp.model.SpielVoting;

import java.util.List;

public class SpielVotingRepository {
    private SpielVotingDao dao;

    public SpielVotingRepository(Context context) {
        dao = AppDatabase.getInstance(context).spielVotingDao();
    }

    public LiveData<List<SpielVoting>> getVotingsForAbend(int abendId) { return dao.getByAbend(abendId); }
    public void hinzufuegen(SpielVoting v)                   { dao.insert(v); }

    public int getGewinnerSpielId(int abendId) {
        return dao.getGewinnerSpielId(abendId);
    }
}