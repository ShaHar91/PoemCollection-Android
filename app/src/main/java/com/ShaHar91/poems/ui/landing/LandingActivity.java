package com.shahar91.poems.ui.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.shahar91.poems.R;
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity;
import com.shahar91.poems.ui.home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LandingActivity extends BaseGoogleMobileActivity<LandingViewModel, LandingComponent> {
    public static void start(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected LandingComponent createComponent() {
        return DaggerLandingComponent.builder()
                .applicationComponent(getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        component.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LandingViewModel.class);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                HomeActivity.start(LandingActivity.this);
            }
        }, 2000L);
    }
}