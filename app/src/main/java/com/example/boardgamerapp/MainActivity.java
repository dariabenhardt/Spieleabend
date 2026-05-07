package com.example.boardgamerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardgamerapp.model.Spieler;
import com.example.boardgamerapp.viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views holen
        TextView tvHallo = findViewById(R.id.tvHallo);
        RecyclerView rvBevorstehend = findViewById(R.id.rvBevorstehend);
        RecyclerView rvVergangen = findViewById(R.id.rvVergangen);

        // Adapter erstellen
        AbendAdapter adapterBevorstehend = new AbendAdapter(this, null, false);
        AbendAdapter adapterVergangen = new AbendAdapter(this, null, true);

        // RecyclerViews einrichten
        rvBevorstehend.setLayoutManager(new LinearLayoutManager(this));
        rvBevorstehend.setAdapter(adapterBevorstehend);

        rvVergangen.setLayoutManager(new LinearLayoutManager(this));
        rvVergangen.setAdapter(adapterVergangen);

        // ViewModel holen
        HomeViewModel viewModel = new ViewModelProvider(this)
                .get(HomeViewModel.class);

        // Hallo-Text setzen
        Spieler ich = viewModel.getAktuellerSpieler();
        if (ich != null) {
            tvHallo.setText("Hallo " + ich.getName() + "!");
        }

        // Bevorstehende Abende beobachten
        viewModel.bevorstehendAbende.observe(this, liste -> {
            adapterBevorstehend.setAbendListe(liste);
        });

        // Vergangene Abende beobachten
        viewModel.vergangeneAbende.observe(this, liste -> {
            adapterVergangen.setAbendListe(liste);
        });

        // Neuer Termin
        LinearLayout newEventButton = findViewById(R.id.new_event_button);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewEventActivity.class);
                startActivity(intent);
            }
        });
    }
}