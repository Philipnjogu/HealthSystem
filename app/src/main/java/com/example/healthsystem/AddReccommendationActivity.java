package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsystem.models.Reccommendation;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddReccommendationActivity extends AppCompatActivity {
    private static final String TAG = "AddReccommendationActiv";
    private EditText bslevelET, bsrecET;
    private Button addBSRecBtn;
    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reccommendation);

        initWidgets();
        mDb = FirebaseFirestore.getInstance();

        addBSRecBtn.setOnClickListener(
                view -> {
                    double bslevel = Double.parseDouble(bslevelET.getText().toString().trim());
                    String bsrec = bsrecET.getText().toString().trim();

                    Reccommendation rec = new Reccommendation(bslevel, bsrec);

                    mDb.collection("reccommendations")
                            .add(rec)
                            .addOnCompleteListener(
                                    task -> {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(this, "Reccommendation Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(this, Bsrec.class));
                                        }else{
                                            Toast.makeText(this, "Operation Failed", Toast.LENGTH_SHORT).show();
                                            Log.e(TAG, "onCreate: Failed to Add Reccommendation", task.getException());
                                        }
                                    }
                            );
                }
        );
    }

    private void initWidgets() {
        bslevelET = findViewById(R.id.bslevel_et);
        bsrecET = findViewById(R.id.bsrec_et);
        addBSRecBtn = findViewById(R.id.add_rec_btn);
    }
}
