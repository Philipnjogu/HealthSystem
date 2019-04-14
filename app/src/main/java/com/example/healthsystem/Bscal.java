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
            Intent intent = new Intent(Bscal.this, Bsrec.class);
            startActivity(intent);
        });

        buttonn = findViewById(R.id.btnbsgen);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bscal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_addrec:
                startActivity(new Intent(this, AddReccommendationActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
