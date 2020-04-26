package com.example.footballmonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Kilpailu> joukkueLista;

    public Adapter(Context ctx, List<Kilpailu> joukkueLista) {
        this.inflater = LayoutInflater.from(ctx);
        this.joukkueLista = joukkueLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.kilpailuLista.setText(joukkueLista.get(position).getCompetition());
    }

    @Override
    public int getItemCount() {
        return joukkueLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kilpailuLista;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kilpailuLista = itemView.findViewById(R.id.kilpailuLista);
        }
    }
}