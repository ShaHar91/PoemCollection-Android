package com.shahar91.poems.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.shahar91.poems.R;
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity;

public class HomeActivity extends BaseGoogleMobileActivity<HomeViewModel, HomeComponent> {
    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected HomeComponent createComponent() {
        return DaggerHomeComponent.builder()
                .applicationComponent(getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        component.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        //TODO: show a list of all subjects
        //TODO: show a list of all poems for that subject
        //TODO: show the asked poem
    }
}
