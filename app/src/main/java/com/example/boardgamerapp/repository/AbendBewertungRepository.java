package com.example.boardgamerapp.repository;

import android.content.Context;

import com.example.boardgamerapp.database.AbendBewertungDao;
import com.example.boardgamerapp.database.AppDatabase;
import com.example.boardgamerapp.model.AbendBewertung;

import java.util.List;

public class AbendBewertungRepository {
    private AbendBewertungDao dao;

    public AbendBewertungRepository(Context context) {
        dao = AppDatabase.getInstance(context).abendBewertungDao();
    }

    public List<AbendBewertung> getBewertungenFuerAbend(int abendId) { return dao.getByAbend(abendId); }
    public void hinzufuegen(AbendBewertung b)                        { dao.insert(b); }
    public void aktualisieren(AbendBewertung b)                      { dao.update(b); }

    public float getDurchschnittEssen(int abendId)      { return dao.getDurchschnittEssen(abendId); }
    public float getDurchschnittAbend(int abendId)      { return dao.getDurchschnittAbend(abendId); }
    public float getDurchschnittGastgeber(int abendId)  { return dao.getDurchschnittGastgeber(abendId); }
}