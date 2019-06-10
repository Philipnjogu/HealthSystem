package com.example.healthsystem.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.healthsystem.models.Chat;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ChatHolder> {

    private static final int CHAT_LEFT = 1, CHAT_RIGHT = 3;
    private List<Chat> chats = null;

    private FirebaseAuth mAuth = null;

    public MessageAdapter(List<Chat> chats) {
        this.chats = chats;

        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder chatHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    @Override
    public int getItemViewType(int position) {

        String currentUid = mAuth.getCurrentUser().getUid();

        Chat chat = chats.get(position);

        if (chat.equals(currentUid)) return CHAT_RIGHT;
        else return CHAT_LEFT;

    }

    public static class ChatHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView msgTV;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
