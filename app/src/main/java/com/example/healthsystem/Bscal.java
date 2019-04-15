package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class Bscal extends AppCompatActivity {


    private EditText bslvl;
    private Button button, buttonn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bscal);
        bslvl = findViewById(R.id.bslevel);
        button = findViewById(R.id.btnbssubmit);

        button.setOnClickListener(view -> {
            double dblbslevel = Double.parseDouble(bslvl.getText().toString().trim());

            Intent intent = new Intent(this, Bsrec.class);

            intent.putExtra(Bsrec.BSLVEL_PARAM, dblbslevel);

            startActivity(intent);
        });

        buttonn = findViewById(R.id.btnbsgen);


    }

}
