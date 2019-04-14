package com.example.healthsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //Widgets
    private EditText emailET, pwdET;
    private Button loginBtn, signupBtn;
    private ProgressDialog progressDialog;

    //Firebase Variables
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        initComponents();

        loginBtn.setOnClickListener(view -> loginUser());
        signupBtn.setOnClickListener(view -> startActivity(new Intent(this, SignupActivity.class)));
    }

    private void loginUser() {
        if(hasEmptyFields()) return;

        String email = emailET.getText().toString().trim();
        String pwd = pwdET.getText().toString().trim();

        progressDialog.setTitle("Attempting Signup");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(
                        task -> {
                            if(task.isSuccessful()){
                                sendToHome();
                            }else{
                                Toast.makeText(this, "Login Error: " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                );
    }

    private void initComponents() {

        emailET = findViewById(R.id.email_et);
        pwdET = findViewById(R.id.pwd_et);

        loginBtn = findViewById(R.id.login_btn);
        signupBtn = findViewById(R.id.signup_btn);

        progressDialog = new ProgressDialog(this);

    }

    private boolean hasEmptyFields() {
        if (emailET.getText().toString().trim().isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return true;
        } else if (pwdET.getText().toString().trim().isEmpty()) {
            pwdET.setError("Password is required");
            pwdET.requestFocus();
            return true;
        }else return false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){
            sendToHome();
        }
    }

    private void sendToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
