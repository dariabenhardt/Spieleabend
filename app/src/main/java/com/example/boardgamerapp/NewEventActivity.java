package com.example.boardgamerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardgamerapp.model.Spieler;
import com.example.boardgamerapp.viewmodel.TerminErstellenViewModel;

public class NewEventActivity extends AppCompatActivity {

    private TerminErstellenViewModel viewModel;

    private Button btnGastgeberAuslosen;
    private Button btnSpieleWaehlen;
    private Button btnTerminErstellen;
    private EditText etUhrzeit;
    private EditText etDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        // ViewModel initialisieren
        viewModel = new ViewModelProvider(this).get(TerminErstellenViewModel.class);

        // Views verknüpfen
        btnGastgeberAuslosen = findViewById(R.id.btnGastgeberAuslosen);
        btnSpieleWaehlen     = findViewById(R.id.btnSpieleWaehlen);
        btnTerminErstellen   = findViewById(R.id.btnTerminErstellen);
        etUhrzeit            = findViewById(R.id.etUhrzeit);
        etDatum              = findViewById(R.id.etDatum);

        // Gastgeber automatisch vorschlagen
        Spieler vorschlag = viewModel.getNaechstenGastgeberVorschlag();
        if (vorschlag != null) {
            viewModel.setGastgeber(vorschlag);
            // Vorgeschlagenen Gastgeber markieren (später mit Chips)
            Toast.makeText(this, "Vorschlag: " + vorschlag.getName(), Toast.LENGTH_SHORT).show();
        }

        // Gastgeber auslosen Button
        btnGastgeberAuslosen.setOnClickListener(v -> {
            Spieler naechster = viewModel.getNaechstenGastgeberVorschlag();
            if (naechster != null) {
                viewModel.setGastgeber(naechster);
                Toast.makeText(this, naechster.getName() + " ist Gastgeber!", Toast.LENGTH_SHORT).show();
            }
        });

        // Termin erstellen Button
        btnTerminErstellen.setOnClickListener(v -> {
            // Werte aus Feldern ins ViewModel schreiben
            viewModel.setUhrzeit(etUhrzeit.getText().toString());
            viewModel.setDatum(etDatum.getText().toString());

            if (viewModel.terminErstellen()) {
                Toast.makeText(this, "Termin erstellt!", Toast.LENGTH_SHORT).show();
                finish(); // zurück zur vorherigen Activity
            } else {
                Toast.makeText(this, "Bitte alle Felder ausfüllen!", Toast.LENGTH_SHORT).show();
            }
        });

        // Spiele wählen Button (später implementieren)
        btnSpieleWaehlen.setOnClickListener(v -> {
            Toast.makeText(this, "Spiele wählen kommt noch!", Toast.LENGTH_SHORT).show();
        });
    }
}