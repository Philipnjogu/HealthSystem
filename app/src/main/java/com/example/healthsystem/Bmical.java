package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmical extends AppCompatActivity {

    private EditText edheight, edweight;
    private Button button, buttonn;
    private TextView set;
    private EditText bmilvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmical);
        Toast.makeText(this,"Hello", Toast.LENGTH_LONG).show();


        edheight = (EditText)findViewById(R.id.height);
        edweight = (EditText)findViewById(R.id.weight);
        button = (Button)findViewById(R.id.btnbmicalculate);
        Button buttonnn = (Button)findViewById(R.id.btnbmisubmit);


        set = (TextView)findViewById(R.id.setbmi);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View view){

                                          if(edheight.toString().isEmpty() || edweight.toString().isEmpty()){
                                              prompt();
                                          }
                                          else{makecalculations();

                                          }
                                      }
                                  }

        );


        buttonnn.setOnClickListener(view->{
            Intent intent = new Intent(Bmical.this, Bmirec.class);
            startActivity(intent);

        });
    }
    private void makecalculations(){
        Double hei = Double.valueOf(edheight.getText().toString());
        Double wei = Double.valueOf(edweight.getText().toString());


        Double bmi = wei / (hei * hei);

        set.setText("The BMI is   " + bmi);
    }


    private void prompt(){
        Toast.makeText(this,"Enter a value in these fields", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bmical_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_addrec:
                startActivity(new Intent(this, BmiAddReccommendationActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
