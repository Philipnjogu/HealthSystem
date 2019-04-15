package com.example.healthsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.healthsystem.fragments.admin.MessagesFragment;
import com.example.healthsystem.fragments.admin.NotificationsFragment;
import com.example.healthsystem.fragments.admin.ReccommendationFragment;
import com.example.healthsystem.fragments.dialogs.AddNotificationDialog;
import com.example.healthsystem.models.Notification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminDashboardActivity extends AppCompatActivity implements AddNotificationDialog.AddNotificationListener {
    private static final String TAG = "AdminDashboardActivity";
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_add_rec:
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle("Reccommendations");
                switchFragment(new ReccommendationFragment());

                return true;
            case R.id.navigation_messages:
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle("Messages");
                switchFragment(new MessagesFragment());
                return true;
            case R.id.navigation_notifications:
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle("Notifications");
                switchFragment(new NotificationsFragment());
                return true;
            default:
                return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        mAuth.signOut();
        startActivity(new Intent(this, StarterActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Reccommendations");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchFragment(new ReccommendationFragment());
    }

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void addNotification(Notification notification) {
        mDb.collection("notification")
                .add(notification)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {

                                Toast.makeText(this, "Notification Addede successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(this, "Try again Later", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "addNotification: ", task.getException());
                            }
                        }
                );
    }

}
