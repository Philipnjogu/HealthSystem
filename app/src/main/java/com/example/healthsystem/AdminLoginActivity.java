package com.example.healthsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsystem.models.AdminDashboardActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLoginActivity extends AppCompatActivity {

    private static final String TAG = "AdminLoginActivity";

    //Widgets
    private EditText emailET, pwdET;
    private Button loginBtn, signupBtn;
    private ProgressDialog progressDialog;

    //Firebase Variables
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        initComponents();

        loginBtn.setOnClickListener(view -> loginUser());
        signupBtn.setOnClickListener(view -> startActivity(new Intent(this, SignupActivity.class)));
    }

    private void loginUser() {
        if (hasEmptyFields()) return;

        String email = emailET.getText().toString().trim();
        String pwd = pwdET.getText().toString().trim();

        progressDialog.setTitle("Attempting Signup");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {

                                sendToHome();

                            } else {
                                progressDialog.dismiss();

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
        } else return false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            sendToHome();
        }
    }

    private void sendToHome() {

        mDb.collection("admin")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(
                        documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                startActivity(new Intent(this, AdminDashboardActivity.class));
                                finish();
                                progressDialog.dismiss();
                            }
                        }
                )
                .addOnFailureListener(
                        e -> {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "loginUser: Login Failed", e);
                            progressDialog.dismiss();
                        }
                );
    }
}
