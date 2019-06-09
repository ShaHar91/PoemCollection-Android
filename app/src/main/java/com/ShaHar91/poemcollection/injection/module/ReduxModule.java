package com.shahar91.poemcollection.injection.module;

import android.util.Log;

import com.shahar91.poemcollection.redux.reducers.ErrorReducer;
import com.yheriatovych.reductor.Store;

import com.shahar91.poemcollection.injection.scope.ApplicationScope;
import com.shahar91.poemcollection.redux.AppState;
import com.shahar91.poemcollection.redux.AppStateReducer;
import dagger.Module;
import dagger.Provides;

@Module
public class ReduxModule {
    public static AppStateReducer getAppStateReducer(){
        Log.d("REDUX_STUFF", "getAppStateReducer: ");
        return AppStateReducer.builder()
                .errorStateReducer(ErrorReducer.create())
                .build();
    }

    @ApplicationScope
    @Provides
    public AppStateReducer provideAppStateReducer() {
        Log.d("REDUX_STUFF", "provideAppStateReducer");
        return getAppStateReducer();
    }

    @ApplicationScope
    @Provides
    public Store<AppState> provideStore(AppStateReducer appStateReducer) {
        Log.d("REDUX_STUFF", "provideStore");
        return Store.create(appStateReducer);
    }
}
