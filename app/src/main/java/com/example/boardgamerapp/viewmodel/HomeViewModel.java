package com.example.boardgamerapp.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;

import com.example.boardgamerapp.model.Abend;
import com.example.boardgamerapp.model.Spieler;
import com.example.boardgamerapp.repository.AbendRepository;
import com.example.boardgamerapp.repository.SpielerRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class HomeViewModel extends AndroidViewModel {
    private AbendRepository abendRepo;
    private SpielerRepository spielerRepo;
    private String heute;

    public HomeViewModel(Application application) {
        super(application);
        abendRepo   = new AbendRepository(application);
        spielerRepo = new SpielerRepository(application);
        heute = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    // Bevorstehende Abende (Datum >= heute)
    public List<Abend> getBevorstehendAbende() {
        return abendRepo.getAlleAbende().stream()
                .filter(a -> a.getDatum().compareTo(heute) >= 0)
                .collect(Collectors.toList());
    }

    // Vergangene Abende (Datum < heute)
    public List<Abend> getVergangeneAbende() {
        return abendRepo.getAlleAbende().stream()
                .filter(a -> a.getDatum().compareTo(heute) < 0)
                .collect(Collectors.toList());
    }

    // Gastgebername für einen Abend
    public String getGastgeberName(int gastgeberId) {
        Spieler s = spielerRepo.getSpielerById(gastgeberId);
        return s != null ? s.getName() : "Unbekannt";
    }

    // Aktuell eingeloggter Spieler (später erweiterbar)
    public Spieler getAktuellerSpieler() {
        List<Spieler> alle = spielerRepo.getAlleSpieler();
        return alle.isEmpty() ? null : alle.get(0);
    }
}