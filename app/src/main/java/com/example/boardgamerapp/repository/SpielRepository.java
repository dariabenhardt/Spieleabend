package com.example.boardgamerapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.boardgamerapp.database.AppDatabase;
import com.example.boardgamerapp.database.SpielDao;
import com.example.boardgamerapp.model.Spiel;

import java.util.List;

public class SpielRepository {
    private SpielDao dao;

    public SpielRepository(Context context) {
        dao = AppDatabase.getInstance(context).spielDao();
    }

    public LiveData<List<Spiel>> getAlleSpiele()   { return dao.getAll(); }
    public void hinzufuegen(Spiel s)    { dao.insert(s); }
    public void loeschen(Spiel s)       { dao.delete(s); }
}
