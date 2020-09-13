package com.shahar91.poems.injection.module;

import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.AppStateReducer;
import com.shahar91.poems.redux.reducers.ErrorReducer;
import com.shahar91.poems.redux.state.ViewStateReducer;
import com.yheriatovych.reductor.Store;

import dagger.Module;
import dagger.Provides;

@Module
public class ReduxModule {
    public static AppStateReducer getAppStateReducer() {
        return AppStateReducer.builder()
                .viewStateReducer(ViewStateReducer.builder()
                        .build())
                .errorStateReducer(ErrorReducer.create())
                .build();
    }

    @ApplicationScope
    @Provides
    public AppStateReducer provideAppStateReducer() {
        return getAppStateReducer();
    }

    @ApplicationScope
    @Provides
    public Store<AppState> provideStore(AppStateReducer appStateReducer) {
        return Store.create(appStateReducer);
    }
}
