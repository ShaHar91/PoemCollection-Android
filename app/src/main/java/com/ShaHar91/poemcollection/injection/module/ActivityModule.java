package com.ShaHar91.poemcollection.injection.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.ShaHar91.poemcollection.injection.qualifier.ActivityContext;
import com.ShaHar91.poemcollection.injection.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @ActivityScope
    @ActivityContext
    @Provides
    Context provideActivityContext() {
        return activity;
    }

    @ActivityScope
    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }
}
