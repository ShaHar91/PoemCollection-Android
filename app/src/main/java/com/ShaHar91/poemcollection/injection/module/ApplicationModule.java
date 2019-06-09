package com.shahar91.poemcollection.injection.module;

import android.content.Context;

import com.shahar91.poemcollection.MyApp;
import com.shahar91.poemcollection.injection.qualifier.ApplicationContext;
import com.shahar91.poemcollection.injection.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MyApp myApp;

    public ApplicationModule(MyApp myApp) {
        this.myApp = myApp;
    }

    @ApplicationScope
    @ApplicationContext
    @Provides
    Context provideApplicationContext() {
        return myApp;
    }
}
