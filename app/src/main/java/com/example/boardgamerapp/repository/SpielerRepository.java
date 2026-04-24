package com.example.boardgamerapp.repository;


import android.content.Context;

import com.example.boardgamerapp.database.AppDatabase;
import com.example.boardgamerapp.database.SpielerDao;
import com.example.boardgamerapp.model.Spieler;

import java.util.List;

public class SpielerRepository {
    private SpielerDao dao;

    public SpielerRepository(Context context) {
        dao = AppDatabase.getInstance(context).spielerDao();
    }

    public List<Spieler> getAlleSpieler()            { return dao.getAll(); }
    public Spieler getSpielerById(int id)            { return dao.getById(id); }
    public void hinzufuegen(Spieler s)               { dao.insert(s); }
    public void aktualisieren(Spieler s)             { dao.update(s); }
    public void loeschen(Spieler s)                  { dao.delete(s); }

    public Spieler getNaechsterGastgeber() {
        List<Spieler> alle = dao.getAll(); // sortiert nach turnusPosition
        return alle.isEmpty() ? null : alle.get(0);
    }
}
