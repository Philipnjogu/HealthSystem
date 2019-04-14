package com.example.healthsystem.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthsystem.R;

import java.util.List;

public class RecsRecyclerViewAdapter extends RecyclerView.Adapter<RecsRecyclerViewAdapter.ViewHolder> {
    List<String> reccommendations;

    public RecsRecyclerViewAdapter(List<String> reccommendations) {
        this.reccommendations = reccommendations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_reccommendation_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String reccommendation = reccommendations.get(i);
        viewHolder.recTV.setText(reccommendation);
    }

    @Override
    public int getItemCount() {
        return reccommendations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView recTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            recTV = itemView.findViewById(R.id.recc_text_tv);
        }
    }
}
