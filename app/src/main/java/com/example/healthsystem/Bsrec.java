package com.example.healthsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.healthsystem.adapters.RecsRecyclerViewAdapter;
import com.example.healthsystem.models.Reccommendation;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Bsrec extends AppCompatActivity {
    private static final String TAG = "Bsrec";
    public static final String BSLVEL_PARAM = "blood-sugar-level";

    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsrec);

        mDb = FirebaseFirestore.getInstance();

        RecyclerView recsRV = findViewById(R.id.recs_rv);
        recsRV.setLayoutManager(new LinearLayoutManager(this));
        recsRV.setHasFixedSize(true);

        List<String> recList = new ArrayList<>();
        RecsRecyclerViewAdapter adapter = new RecsRecyclerViewAdapter(recList);
        recsRV.setAdapter(adapter);

        double bslevel = 0.0;

        if(getIntent() != null){
            bslevel = getIntent().getDoubleExtra(BSLVEL_PARAM, 0.0);
        }

        mDb.collection("reccommendations")
                .whereGreaterThan("bslevel", bslevel)
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {
                            if (e != null) {
                                Log.e(TAG, "onCreate: Error getting Docs", e);
                                return;
                            }

                            if (!queryDocumentSnapshots.isEmpty()) {
                                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                    Reccommendation rec = doc.toObject(Reccommendation.class);

                                    recList.add(rec.getBsrec());
                                    adapter.notifyDataSetChanged();

                                }
                            }else{
                                Toast.makeText(this, "No Reccommendations", Toast.LENGTH_SHORT).show();
                            }
                        }
                );


    }
}
