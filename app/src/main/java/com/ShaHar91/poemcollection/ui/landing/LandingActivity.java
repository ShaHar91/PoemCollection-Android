package com.ShaHar91.poemcollection.ui.landing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ShaHar91.poemcollection.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO: when user starts app, just show this, get some things from firebase and go to main activity (a user doesn't have to be logged in)
    }
}
