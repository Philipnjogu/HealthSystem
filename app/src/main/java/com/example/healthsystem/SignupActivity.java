package com.example.healthsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText emailET, pwdET;
    private Button signupBtn, loginBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        initComponents();

        loginBtn.setOnClickListener(view -> sendToStart());

        signupBtn.setOnClickListener(view -> createUser());

    }

    private void createUser() {

        if (!validated()) return;

        String email = String.valueOf(emailET.getTotalPaddingEnd());
        String pwd = String.valueOf(pwdET.getTotalPaddingEnd());

        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Error: " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private boolean validated() {

        if (TextUtils.isEmpty(emailET.getText())) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pwdET.getText())) {
            pwdET.setError("Password is required");
            pwdET.requestFocus();
            return false;
        } else return true;

    }

    private void sendToStart() {
        Intent intent = new Intent(this, StarterActivity.class);
        startActivity(intent);
    }

    private void initComponents() {
        emailET = findViewById(R.id.email_et);
        pwdET = findViewById(R.id.pwd_et);
        signupBtn = findViewById(R.id.signup_btn);
        loginBtn = findViewById(R.id.login_btn);
    }
}
