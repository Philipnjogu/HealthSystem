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

public class BpAddReccommendationActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bp_add_reccommendation);
//    }

    private static final String TAG = "AddReccommendationActiv";
    private EditText bplevelET, bprecET;
    private Button addBPRecBtn;

    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reccommendation);

        initWidgets();
        mDb = FirebaseFirestore.getInstance();

//        addBPRecBtn.setOnClickListener(
//                view -> {
//                    double bplevel = Double.parseDouble(bplevelET.getText().toString().trim());
//                    String bprec = bprecET.getText().toString().trim();
//
//                    Reccommendation rec = new Reccommendation(bplevel, bprec);
//
//                    mDb.collection("reccommendations")
//                            .add(rec)
//                            .addOnCompleteListener(
//                                    task -> {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(this, "Reccommendation Added", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(this, Bprec.class));
//                                        }else{
//                                            Toast.makeText(this, "Operation Failed", Toast.LENGTH_SHORT).show();
//                                            Log.e(TAG, "onCreate: Failed to Add Reccommendation", task.getException());
//                                        }
//                                    }
//                            );
//                }
//        );
    }

    private void initWidgets() {
        bplevelET = findViewById(R.id.bplevel_et);
        bprecET = findViewById(R.id.bprec_et);
        addBPRecBtn = findViewById(R.id.add_rec_btn);
    }

}
