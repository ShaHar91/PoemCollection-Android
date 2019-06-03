package com.ShaHar91.poemcollection.ui.landing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ShaHar91.poemcollection.R;
import com.ShaHar91.poemcollection.ui.main.MainActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //TODO: when user starts app, just show this, get some things from firebase and go to main activity (a user doesn't have to be logged in)

        MainActivity.start(this);
    }
}