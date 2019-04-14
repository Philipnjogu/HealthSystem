package com.example.healthsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
private EditText EMAILET, PWDET;
private Button SIGNUPBTN, LOGINBTN;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

EMAILET = findViewById(R.id.email_et);
PWDET = findViewById(R.id.pwd_et);
SIGNUPBTN = findViewById(R.id.signup_btn);
LOGINBTN = findViewById(R.id.login_btn);



    }
}
