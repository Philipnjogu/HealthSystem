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

public class BmiAddReccommendationActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bmi_add_reccommendation);
//    }
private static final String TAG = "AddReccommendationActiv";
    private EditText bmilevelET, bmirecET;
    private Button addBMIRecBtn;

    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reccommendation);

        initWidgets();
        mDb = FirebaseFirestore.getInstance();

        addBMIRecBtn.setOnClickListener(
                view -> {
                    double bmilevel = Double.parseDouble(bmilevelET.getText().toString().trim());
                    String bmirec = bmirecET.getText().toString().trim();

                    Reccommendation rec = new Reccommendation(bmilevel, bmirec);

                    mDb.collection("reccommendations")
                            .add(rec)
                            .addOnCompleteListener(
                                    task -> {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(this, "Reccommendation Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(this, Bmirec.class));
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
        bmilevelET = findViewById(R.id.bmilevel_et);
        bmirecET = findViewById(R.id.bmirec_et);
        addBMIRecBtn = findViewById(R.id.add_rec_btn);
    }
}
