package com.example.boardgamerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardgamerapp.model.Spiel;
import com.example.boardgamerapp.model.SpielVoting;
import com.example.boardgamerapp.repository.SpielRepository;
import com.example.boardgamerapp.repository.SpielVotingRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Views finden
        TextView tvEventTitle = findViewById(R.id.tvEventTitle);
        TextView tvEventDate = findViewById(R.id.tvEventDate);
        TextView tvEventTime = findViewById(R.id.tvEventTime);
        ImageView btnClose = findViewById(R.id.btnClose);

        // putExtra-Daten auslesen
        String eventName = getIntent().getStringExtra("eventName");
        String eventDate = getIntent().getStringExtra("eventDate");
        String eventTime = getIntent().getStringExtra("eventTime");
        String eventLocation = getIntent().getStringExtra("eventLocation");

        // Daten anzeigen
        tvEventTitle.setText(eventName);
        tvEventDate.setText("Datum: " + eventDate);
        tvEventTime.setText("Uhrzeit: " + eventTime + " Uhr");

        // Close-Button
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Schließt die Activity
            }
        });

        // Spiele-RecyclerView einrichten
        RecyclerView rvSpiele = findViewById(R.id.rvSpiele);
        SpielRepository spielRepo = new SpielRepository(this);

        // Spiele aus Datenbank holen
        int eventId = getIntent().getIntExtra("eventId", -1);
        List<Spiel> eventSpiele = getSpieleFuerEvent(eventId);

        // Adapter erstellen und setzen
        SpielAdapter spielAdapter = new SpielAdapter(this, eventSpiele);

        rvSpiele.setLayoutManager(new LinearLayoutManager(this));
        rvSpiele.setAdapter(spielAdapter);

        // Weitere Spiele vorschlagen
        Button btnWeitereSpiele = findViewById(R.id.btnWeitereSpiele);
        btnWeitereSpiele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeigeVerfuegbareSpiele();
            }
        });
    }

    private List<Spiel> getSpieleFuerEvent(int eventId) {
        List<Spiel> eventSpiele = new ArrayList<>();

        // Alle Votings für dieses Event laden
        SpielVotingRepository votingRepo = new SpielVotingRepository(this);
        List<SpielVoting> eventVotings = votingRepo.getVotingsForAbendSync(eventId);

        // Spiel-IDs extrahieren
        Set<Integer> spielIds = new HashSet<>();  // Set verhindert Duplikate
        for (SpielVoting voting : eventVotings) {
            spielIds.add(voting.getSpielId());
        }

        // Spiele anhand der IDs laden
        SpielRepository spielRepo = new SpielRepository(this);
        List<Spiel> alleSpiele = spielRepo.getAlleSpieleSync();

        // Nur die Spiele für dieses Event filtern
        for (Spiel spiel : alleSpiele) {
            if (spielIds.contains(spiel.getId())) {
                eventSpiele.add(spiel);
            }
        }

        return eventSpiele;
    }
    private void zeigeVerfuegbareSpiele() {
        // Alle Spiele aus DB holen
        SpielRepository spielRepo = new SpielRepository(this);
        List<Spiel> alleSpiele = spielRepo.getAlleSpieleSync();

        // Bereits für dieses Event ausgewählte Spiele
        int eventId = getIntent().getIntExtra("eventId", -1);
        List<Spiel> eventSpiele = getSpieleFuerEvent(eventId);

        // Verfügbare Spiele = Alle MINUS bereits ausgewählte
        List<Spiel> verfuegbareSpiele = new ArrayList<>();
        for (Spiel spiel : alleSpiele) {
            boolean istBereitsAusgewaehlt = false;
            for (Spiel eventSpiel : eventSpiele) {
                if (spiel.getId() == eventSpiel.getId()) {
                    istBereitsAusgewaehlt = true;
                    break;
                }
            }
            if (!istBereitsAusgewaehlt) {
                verfuegbareSpiele.add(spiel);
            }
        }

        // Dialog mit verfügbaren Spielen anzeigen
        zeigeSpielAuswahlDialog(verfuegbareSpiele, eventId);
    }

    private void zeigeSpielAuswahlDialog(List<Spiel> verfuegbareSpiele, int eventId) {
        if (verfuegbareSpiele.isEmpty()) {
            Toast.makeText(this, "Alle Spiele sind bereits ausgewählt!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Spiel-Namen für Dialog vorbereiten
        String[] spielNamen = new String[verfuegbareSpiele.size()];
        for (int i = 0; i < verfuegbareSpiele.size(); i++) {
            spielNamen[i] = verfuegbareSpiele.get(i).getName();
        }

        // Array für ausgewählte Items
        boolean[] ausgewaehlt = new boolean[verfuegbareSpiele.size()];

        // AlertDialog mit Multi-Choice
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Spiele zum Event hinzufügen");

        builder.setMultiChoiceItems(spielNamen, ausgewaehlt, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                ausgewaehlt[which] = isChecked;
            }
        });

        // Hinzufügen Button
        builder.setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Alle ausgewählten Spiele hinzufügen
                List<Spiel> ausgewaehlteSpiele = new ArrayList<>();
                for (int i = 0; i < ausgewaehlt.length; i++) {
                    if (ausgewaehlt[i]) {
                        ausgewaehlteSpiele.add(verfuegbareSpiele.get(i));
                    }
                }

                if (!ausgewaehlteSpiele.isEmpty()) {
                    mehrereSpieleHinzufuegen(ausgewaehlteSpiele, eventId);
                }
            }
        });

        builder.setNegativeButton("Abbrechen", null);
        builder.show();
    }

    private void mehrereSpieleHinzufuegen(List<Spiel> spiele, int eventId) {
        int currentUserId = 1; // Max als Beispiel
        SpielVotingRepository votingRepo = new SpielVotingRepository(this);

        // Alle ausgewählten Spiele zur DB hinzufügen
        for (Spiel spiel : spiele) {
            SpielVoting neuesVoting = new SpielVoting(currentUserId, spiel.getId(), eventId);
            votingRepo.hinzufuegen(neuesVoting);
        }

        // RecyclerView aktualisieren
        List<Spiel> eventSpiele = getSpieleFuerEvent(eventId);
        SpielAdapter spielAdapter = new SpielAdapter(this, eventSpiele);
        RecyclerView rvSpiele = findViewById(R.id.rvSpiele);
        rvSpiele.setAdapter(spielAdapter);

        // Toast für alle hinzugefügten Spiele
        String message = spiele.size() + " Spiele zum Event hinzugefügt!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}