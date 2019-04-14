package com.example.healthsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Homefragment extends Fragment {

    public Homefragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        CardView button = view.findViewById(R.id.bmi);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Bmical.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });

        CardView buttonn = view.findViewById(R.id.bp);

        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Bpcal.class);
                intent.putExtra("some", "some data");
                startActivity(intent);
            }
        });

        CardView buttonnn = view.findViewById(R.id.bs);
        buttonnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Bscal.class);
                intent.putExtra("some", "some data");
                startActivity(intent);

            }
        });


        return view;
    }
}
