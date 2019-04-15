package com.example.healthsystem.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthsystem.R;
import com.example.healthsystem.models.Notification;

import java.util.List;

public class AdminNotRecyclerViewAdapter extends RecyclerView.Adapter<AdminNotRecyclerViewAdapter.ViewHolder> {

    List<Notification> notifications;

    public AdminNotRecyclerViewAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_notification_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Notification not = notifications.get(i);
        viewHolder.descTV.setText(not.getDesc());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView descTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            descTV = itemView.findViewById(R.id.desc_tv);
        }
    }
}
