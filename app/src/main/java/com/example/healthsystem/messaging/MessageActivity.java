package com.example.healthsystem.messaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.healthsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView messagesRV;
    private ImageButton sendIB;
    private EditText msgET;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        registerWidgets();
    }

    private void registerWidgets() {

        messagesRV = findViewById(R.id.messages_rv);
        sendIB = findViewById(R.id.send_ib);
        msgET = findViewById(R.id.msg_et);


    }
}
