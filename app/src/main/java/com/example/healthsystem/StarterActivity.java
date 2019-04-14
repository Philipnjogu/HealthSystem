package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StarterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        if(getSupportActionBar() != null) getSupportActionBar().setTitle("User Login");

        Button userLoginBtn = findViewById(R.id.user_login_btn);
        userLoginBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });



        Button adminLoginBtn = findViewById(R.id.admin_login_btn);
        adminLoginBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, AdminLoginActivity.class));
        });
    }
}
