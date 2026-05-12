package com.example.boardgamerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardgamerapp.model.Spiel;
import com.example.boardgamerapp.repository.SpielRepository;

import java.util.List;

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
        List<Spiel> alleSpiele = spielRepo.getAlleSpiele();

        // Adapter erstellen und setzen
        SpielAdapter spielAdapter = new SpielAdapter(this, alleSpiele);
        rvSpiele.setLayoutManager(new LinearLayoutManager(this));
        rvSpiele.setAdapter(spielAdapter);
    }
}