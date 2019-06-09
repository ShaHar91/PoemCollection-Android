package com.ShaHar91.poemcollection.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ShaHar91.poemcollection.MyApp;
import com.ShaHar91.poemcollection.R;
import com.ShaHar91.poemcollection.ui.base.normal.BaseGoogleMobileActivity;

import javax.inject.Inject;

public class MainActivity extends BaseGoogleMobileActivity<MainViewModel, MainComponent> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public static void start(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected MainComponent createComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(((MyApp) getApplication()).getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        //TODO: show a list of all subjects
        //TODO: show a list of all poems for that subject
        //TODO: show the asked poem
    }
}
