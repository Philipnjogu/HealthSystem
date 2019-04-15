package com.example.healthsystem.fragments.admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthsystem.AddReccommendationActivity;
import com.example.healthsystem.R;
import com.example.healthsystem.adapters.RecsRecyclerViewAdapter;
import com.example.healthsystem.models.Reccommendation;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class ReccommendationFragment extends Fragment {

    private static final String TAG = "ReccommendationFragment";

    private FirebaseFirestore mDb;
    private List<String> recList;
    private RecsRecyclerViewAdapter adapter;

    public ReccommendationFragment() {
        mDb = FirebaseFirestore.getInstance();
        recList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reccommendation, container, false);

        RecyclerView recsRV = view.findViewById(R.id.recs_rv);
        recsRV.setHasFixedSize(true);
        recsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecsRecyclerViewAdapter(recList);
        recsRV.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> startActivity(new Intent(getActivity(), AddReccommendationActivity.class)));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDb.collection("reccommendations")
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {
                            if (e != null) {
                                Log.e(TAG, "onCreate: Error getting Docs", e);
                                return;
                            }
                            recList.clear();
                            if (!queryDocumentSnapshots.isEmpty()) {
                                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                    Reccommendation rec = doc.toObject(Reccommendation.class);

                                    recList.add(rec.getBsrec());
                                    adapter.notifyDataSetChanged();

                                }
                            } else {
                                Toast.makeText(getActivity(), "No Reccommendations", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
    }
}
