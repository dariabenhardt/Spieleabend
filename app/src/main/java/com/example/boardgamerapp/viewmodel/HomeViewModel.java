package com.example.boardgamerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

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

    public LiveData<List<Abend>> bevorstehendAbende;
    public LiveData<List<Abend>> vergangeneAbende;

    public HomeViewModel(Application application) {
        super(application);
        abendRepo   = new AbendRepository(application);
        spielerRepo = new SpielerRepository(application);
        heute = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        LiveData<List<Abend>> alleAbende = abendRepo.getAlleAbende();

        // Filtert Abende >= heute, begrenzt auf 2
        bevorstehendAbende = Transformations.map(alleAbende, liste ->
                liste.stream()
                        .filter(a -> a.getDatum().compareTo(heute) >= 0)
                        .sorted((a1, a2) -> a1.getDatum().compareTo(a2.getDatum()))
                        .limit(2)
                        .collect(Collectors.toList())
        );

        // Filtert Abende < heute, begrenzt auf 3
        vergangeneAbende = Transformations.map(alleAbende, liste ->
                liste.stream()
                        .filter(a -> a.getDatum().compareTo(heute) < 0)
                        .sorted((a1, a2) -> a2.getDatum().compareTo(a1.getDatum()))
                        .limit(3)
                        .collect(Collectors.toList())
        );
    }

    // Gastgebername für einen Abend
    public String getGastgeberName(int gastgeberId) {
        Spieler s = spielerRepo.getSpielerById(gastgeberId);
        return s != null ? s.getName() : "Unbekannt";
    }

    // Aktuell eingeloggter Spieler (erster Spieler in der DB)
    public Spieler getAktuellerSpieler() {
        List<Spieler> alle = spielerRepo.getAlleSpieler();
        return alle.isEmpty() ? null : alle.get(0);
    }

    // Nächsten Abend direkt abrufen
    public Abend getNaechstenAbend() {
        return abendRepo.getNaechstenAbend();
    }
}