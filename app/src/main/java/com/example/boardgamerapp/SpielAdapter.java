package com.example.boardgamerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boardgamerapp.model.Spiel;

import java.util.List;

public class SpielAdapter extends RecyclerView.Adapter<SpielAdapter.SpielViewHolder> {

    private List<Spiel> spieleListe;
    private Context context;

    public SpielAdapter(Context context, List<Spiel> spieleListe) {
        this.context = context;
        this.spieleListe = spieleListe;
    }

    @NonNull
    @Override
    public SpielViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_spiel, parent, false);
        return new SpielViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpielViewHolder holder, int position) {
        Spiel spiel = spieleListe.get(position);
        holder.checkBox.setText(spiel.getName());
    }

    @Override
    public int getItemCount() {
        return spieleListe != null ? spieleListe.size() : 0;
    }

    public void setSpieleListe(List<Spiel> neueListe) {
        this.spieleListe = neueListe;
        notifyDataSetChanged();
    }

    public static class SpielViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public SpielViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxSpiel);
        }
    }
}