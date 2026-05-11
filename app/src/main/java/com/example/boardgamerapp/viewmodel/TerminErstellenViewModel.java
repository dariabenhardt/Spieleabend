package com.example.boardgamerapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.boardgamerapp.model.Abend;
import com.example.boardgamerapp.model.Spiel;
import com.example.boardgamerapp.model.Spieler;
import com.example.boardgamerapp.repository.AbendRepository;
import com.example.boardgamerapp.repository.SpielRepository;
import com.example.boardgamerapp.repository.SpielerRepository;

import java.util.ArrayList;
import java.util.List;

public class TerminErstellenViewModel extends AndroidViewModel {

    private AbendRepository abendRepo;
    private SpielerRepository spielerRepo;
    private SpielRepository spielRepo;

    // Ausgewählte Werte für den neuen Termin
    private Spieler ausgewaehlterGastgeber;
    private String uhrzeit;
    private String datum;
    private List<Spiel> ausgewaehlteSpiele = new ArrayList<>();

    public TerminErstellenViewModel(Application application) {
        super(application);
        abendRepo   = new AbendRepository(application);
        spielerRepo = new SpielerRepository(application);
        spielRepo   = new SpielRepository(application);
    }

    // ── Gastgeber ──────────────────────────────────────────

    // Alle Spieler für Auswahl anzeigen
    public List<Spieler> getAlleSpieler() {
        return spielerRepo.getAlleSpieler();
    }

    // Nächsten Gastgeber per Turnus vorschlagen
    public Spieler getNaechstenGastgeberVorschlag() {
        Abend letzter = abendRepo.getLetztenAbend();
        if (letzter == null) {
            // Noch kein Abend → ersten Spieler nehmen
            List<Spieler> alle = spielerRepo.getAlleSpieler();
            return alle.isEmpty() ? null : alle.get(0);
        }
        return spielerRepo.getNaechsterGastgeber(letzter.getGastgeberId());
    }

    public void setGastgeber(Spieler spieler) {
        this.ausgewaehlterGastgeber = spieler;
    }

    public Spieler getAusgewaehlterGastgeber() {
        return ausgewaehlterGastgeber;
    }

    // ── Datum & Uhrzeit ────────────────────────────────────

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getDatum()   { return datum; }
    public String getUhrzeit() { return uhrzeit; }

    // ── Spiele ─────────────────────────────────────────────

    public LiveData<List<Spiel>> getAlleSpiele() {
        return spielRepo.getAlleSpiele();
    }

    public void spielHinzufuegen(Spiel spiel) {
        if (!ausgewaehlteSpiele.contains(spiel)) {
            ausgewaehlteSpiele.add(spiel);
        }
    }

    public void spielEntfernen(Spiel spiel) {
        ausgewaehlteSpiele.remove(spiel);
    }

    public List<Spiel> getAusgewaehlteSpiele() {
        return ausgewaehlteSpiele;
    }

    // ── Termin speichern ───────────────────────────────────

    public boolean terminErstellen() {
        String u = uhrzeit != null ? uhrzeit.trim() : "";
        String d = datum != null ? datum.trim() : "";

        if (ausgewaehlterGastgeber == null || u.isEmpty() || d.isEmpty()) {
            return false;
        }

        Abend neuerAbend = new Abend(u, d, ausgewaehlterGastgeber.getId());
        abendRepo.hinzufuegen(neuerAbend);
        return true;
    }
}
