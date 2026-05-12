package com.example.boardgamerapp.repository;


import android.content.Context;

import androidx.lifecycle.LiveData;

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

    public Spieler getNaechsterGastgeber(int letzterGastgeberId) {
        List<Spieler> alle = dao.getAll(); // sortiert nach turnusPosition ASC
        if (alle.isEmpty()) return null;

        // Letzte turnusPosition finden
        int letztePosition = 0;
        for (Spieler s : alle) {
            if (s.getId() == letzterGastgeberId) {
                letztePosition = s.getTurnusPosition();
                break;
            }
        }

        // Nächsten Spieler im Turnus suchen
        for (Spieler s : alle) {
            if (s.getTurnusPosition() > letztePosition) {
                return s; // nächster in der Reihe
            }
        }

        // Am Ende der Liste → wieder von vorne (Rundum-Turnus)
        return alle.get(0);
    }
}
