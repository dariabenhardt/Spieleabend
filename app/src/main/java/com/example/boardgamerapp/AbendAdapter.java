package com.example.boardgamerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardgamerapp.model.Abend;
import com.example.boardgamerapp.model.Spieler;
import com.example.boardgamerapp.repository.SpielerRepository;

import java.util.List;

public class AbendAdapter extends RecyclerView.Adapter<AbendAdapter.AbendViewHolder> {

    private List<Abend> abendListe;
    private Context context;
    private SpielerRepository spielerRepo;

    public AbendAdapter(Context context, List<Abend> abendListe) {
        this.context = context;
        this.abendListe = abendListe;
        this.spielerRepo = new SpielerRepository(context);
    }

    @NonNull
    @Override
    public AbendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_abend, parent, false);
        return new AbendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbendViewHolder holder, int position) {
        Abend abend = abendListe.get(position);

        // Gastgeber aus DB holen
        Spieler gastgeber = spielerRepo.getSpielerById(abend.getGastgeberId());
        if (gastgeber != null) {
            holder.tvGastgeber.setText("Spieleabend bei " + gastgeber.getName());
            holder.tvOrt.setText("Ort: " + gastgeber.getAdresse());
        }

        holder.tvDatum.setText("Datum: " + abend.getDatum());
        holder.tvZeit.setText("Uhrzeit: " + abend.getZeit() + " Uhr");
    }

    @Override
    public int getItemCount() {
        return abendListe != null ? abendListe.size() : 0;
    }

    // Liste aktualisieren wenn LiveData sich ändert
    public void setAbendListe(List<Abend> neueListe) {
        this.abendListe = neueListe;
        notifyDataSetChanged();
    }

    // ViewHolder
    public static class AbendViewHolder extends RecyclerView.ViewHolder {
        TextView tvGastgeber, tvDatum, tvZeit, tvOrt;
        Button btnMehrInfos;

        public AbendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGastgeber = itemView.findViewById(R.id.tvGastgeber);
            tvDatum     = itemView.findViewById(R.id.tvDatum);
            tvZeit      = itemView.findViewById(R.id.tvZeit);
            tvOrt       = itemView.findViewById(R.id.tvOrt);
            btnMehrInfos = itemView.findViewById(R.id.btnMehrInfos);
        }
    }
}