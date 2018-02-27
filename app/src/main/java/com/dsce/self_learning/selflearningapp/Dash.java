package com.dsce.self_learning.selflearningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Dash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash);

    }

    public void logout(View view) {
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        mauth.signOut();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}