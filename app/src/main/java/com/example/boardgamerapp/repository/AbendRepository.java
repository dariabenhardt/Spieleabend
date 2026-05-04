package com.example.boardgamerapp.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.boardgamerapp.database.AbendDao;
import com.example.boardgamerapp.database.AppDatabase;
import com.example.boardgamerapp.model.Abend;
import com.example.boardgamerapp.model.Spieler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AbendRepository {
    private AbendDao dao;
    public AbendRepository(Context context) {
        dao = AppDatabase.getInstance(context).abendDao();
    }

    public LiveData<List<Abend>> getAlleAbende()            { return dao.getAll(); }
    public Abend getAbendById(int id)            { return dao.getById(id); }
    public void hinzufuegen(Abend a)               { dao.insert(a); }
    public void aktualisieren(Abend a)             { dao.update(a); }
    public void loeschen(Abend a)           { dao.delete(a); }

    public Abend getNaechstenAbend() {
        String heute = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(new Date());
        return dao.getNaechstenAbend(heute);
    }
}
