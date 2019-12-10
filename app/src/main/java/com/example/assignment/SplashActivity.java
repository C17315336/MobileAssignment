package com.example.assignment;

// Imports Section
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/*************************************
 *
 * Assignment        : Mobile Software Development CA
 * Package           : com.example.assignment
 * File              : SplashActivity.java
 * Author            : Eoghan Byrne
 * Student number    : C17315336
 * Last modified     : 10th December 2019
 *
 *************************************/

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start main activity
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        // Close splash activity
        finish();
    }
}
