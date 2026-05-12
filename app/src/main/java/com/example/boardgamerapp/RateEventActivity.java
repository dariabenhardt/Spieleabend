package com.example.boardgamerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RateEventActivity extends AppCompatActivity {

    // Rating Variablen
    private int gastgeberRating = 0;
    private int essenRating = 0;
    private int gesamtRating = 0;

    // Stern-Arrays
    private ImageView[] gastgeberSterne = new ImageView[5];
    private ImageView[] essenSterne = new ImageView[5];
    private ImageView[] gesamtSterne = new ImageView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rate_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Views finden
        ImageView btnClose = findViewById(R.id.btnClose);

        // Close-Button
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Schließt die Activity
            }
        });

        initStars();
        setupStarClickListeners();

    }

    private void initStars() {
        // Gastgeber-Sterne Arrays befüllen
        gastgeberSterne[0] = findViewById(R.id.starGastgeber1);
        gastgeberSterne[1] = findViewById(R.id.starGastgeber2);
        gastgeberSterne[2] = findViewById(R.id.starGastgeber3);
        gastgeberSterne[3] = findViewById(R.id.starGastgeber4);
        gastgeberSterne[4] = findViewById(R.id.starGastgeber5);

        // Essen-Sterne Arrays befüllen
        essenSterne[0] = findViewById(R.id.starEssen1);
        essenSterne[1] = findViewById(R.id.starEssen2);
        essenSterne[2] = findViewById(R.id.starEssen3);
        essenSterne[3] = findViewById(R.id.starEssen4);
        essenSterne[4] = findViewById(R.id.starEssen5);

        // Gesamt-Sterne Arrays befüllen
        gesamtSterne[0] = findViewById(R.id.starGesamt1);
        gesamtSterne[1] = findViewById(R.id.starGesamt2);
        gesamtSterne[2] = findViewById(R.id.starGesamt3);
        gesamtSterne[3] = findViewById(R.id.starGesamt4);
        gesamtSterne[4] = findViewById(R.id.starGesamt5);
    }

    private void setupStarClickListeners() {
        // Gastgeber-Sterne OnClick
        for (int i = 0; i < 5; i++) {
            final int sternNummer = i + 1;
            gastgeberSterne[i].setOnClickListener(v -> {
                gastgeberRating = sternNummer;
                updateGastgeberSterne();
            });
        }

        // Essen-Sterne OnClick
        for (int i = 0; i < 5; i++) {
            final int sternNummer = i + 1;
            essenSterne[i].setOnClickListener(v -> {
                essenRating = sternNummer;
                updateEssenSterne();
            });
        }

        // Gesamt-Sterne OnClick
        for (int i = 0; i < 5; i++) {
            final int sternNummer = i + 1;
            gesamtSterne[i].setOnClickListener(v -> {
                gesamtRating = sternNummer;
                updateGesamtSterne();
            });
        }
    }

    private void updateGastgeberSterne() {
        for (int i = 0; i < 5; i++) {
            if (i < gastgeberRating) {
                gastgeberSterne[i].setImageResource(R.drawable.ic_star_filled_24);
            } else {
                gastgeberSterne[i].setImageResource(R.drawable.ic_star_outline);
            }
        }
    }

    private void updateEssenSterne() {
        for (int i = 0; i < 5; i++) {
            if (i < essenRating) {
                essenSterne[i].setImageResource(R.drawable.ic_star_filled_24);
            } else {
                essenSterne[i].setImageResource(R.drawable.ic_star_outline);
            }
        }
    }

    private void updateGesamtSterne() {
        for (int i = 0; i < 5; i++) {
            if (i < gesamtRating) {
                gesamtSterne[i].setImageResource(R.drawable.ic_star_filled_24);
            } else {
                gesamtSterne[i].setImageResource(R.drawable.ic_star_outline);
            }
        }
    }
}
