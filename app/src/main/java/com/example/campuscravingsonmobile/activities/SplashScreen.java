package com.example.campuscravingsonmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campuscravingsonmobile.R;
import com.google.firebase.FirebaseApp;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Redirect to LoginActivity after a delay
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish splash activity
        }, 2000); // Delay of 2 seconds
    }
}
