package com.dsce.self_learning.selflearningapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class InstructorAuthActivity extends AppCompatActivity {

    TabLayout tab;
    LoginPageAdapter pageAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView dsce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_auth);

        mAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() !=null)
                    {
                        startActivity(new Intent(InstructorAuthActivity.this,Dash.class));
                    }
                }
            };

            Typeface ubuntu = Typeface.createFromAsset(this.getAssets(),
                    "fonts/Ubuntu-L.ttf");

            tab = (TabLayout) findViewById(R.id.tabs);
            tab.addTab(tab.newTab().setText("LogIn"));
            tab.addTab(tab.newTab().setText("SignUp"));
            tab.setTabGravity(tab.GRAVITY_FILL);


            final ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
            pageAdapter = new LoginPageAdapter(getSupportFragmentManager(),tab.getTabCount());
            viewPager.setAdapter(pageAdapter);

            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
            tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }

        @Override
        public void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }
    }


