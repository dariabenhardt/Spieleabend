package com.example.boardgamerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private boolean istVergangen;

    public AbendAdapter(Context context, List<Abend> abendListe) {
        this.context = context;
        this.abendListe = abendListe;
        this.istVergangen = false;
        this.spielerRepo = new SpielerRepository(context);
    }

    public AbendAdapter(Context context, List<Abend> abendListe, boolean vergangen) {
        this.context = context;
        this.abendListe = abendListe;
        this.istVergangen = vergangen;
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

        // Button-Text setzen
        if (istVergangen) {
            holder.btnMehrInfos.setText("Bewerten");
        } else {
            holder.btnMehrInfos.setText("Mehr Infos");
        }

        //Card-Styling hinzufügen
        if (istVergangen) {
            // Vergangene Events: Weiße Cards + hellblauer Button
            holder.btnMehrInfos.setBackgroundColor(Color.parseColor("#CFD0F2")); // Hellblau
            holder.btnMehrInfos.setTextColor(Color.parseColor("#000000"));       // Schwarzer Text

            // Card-Hintergrund weiß
            if (holder.itemView instanceof androidx.cardview.widget.CardView) {
                androidx.cardview.widget.CardView cardView = (androidx.cardview.widget.CardView) holder.itemView;
                holder.itemView.setBackgroundResource(R.drawable.card_background_border);
                cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardView.setCardElevation(1f);
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

        } else {
            // Bevorstehende Events: Hellblaue Cards + dunkler Button
            holder.btnMehrInfos.setBackgroundColor(Color.parseColor("#05031C"));
            holder.btnMehrInfos.setTextColor(Color.parseColor("#FFFFFF"));

            // Card-Hintergrund hellblau
            if (holder.itemView instanceof androidx.cardview.widget.CardView) {
                androidx.cardview.widget.CardView cardView = (androidx.cardview.widget.CardView) holder.itemView;
                cardView.setCardBackgroundColor(Color.parseColor("#CFD0F2"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#CFD0F2"));
            }
        }

        // OnClickListener setzen
        holder.btnMehrInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (istVergangen) {
                    //Zur RateEventActivity
                    Intent intent = new Intent(context, RateEventActivity.class);
                    context.startActivity(intent);
                } else {
                    // Zur EventDetailsActivity
                    Intent intent = new Intent(context, EventDetailsActivity.class);

                    // Event-Daten mitgeben
                    intent.putExtra("eventName", "Spieleabend bei " + gastgeber.getName());
                    intent.putExtra("eventDate", abend.getDatum());
                    intent.putExtra("eventTime", abend.getZeit());
                    intent.putExtra("eventLocation", gastgeber.getAdresse());

                    intent.putExtra("eventId", abend.getId());

                    context.startActivity(intent);
                }
            }
        });
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