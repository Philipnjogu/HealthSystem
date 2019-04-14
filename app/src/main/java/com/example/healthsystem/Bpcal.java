package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bpcal extends AppCompatActivity {


    private EditText bpsystole, bpdystole;
    private Button button, buttonn;
    private EditText bplvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpcal);

        bpsystole = findViewById(R.id.systole);
        bpsystole = findViewById(R.id.dystole);
        button =  findViewById(R.id.btnbpsubmit);
    button.setOnClickListener(view->{
                Intent intent = new Intent(Bpcal.this, Bprec.class);
                startActivity(intent);
            }

            );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bpcal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_addrec:
                startActivity(new Intent(this, BpAddReccommendationActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
