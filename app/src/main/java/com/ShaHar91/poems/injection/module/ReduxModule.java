package com.shahar91.poems.injection.module;

import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.AppStateReducer;
import com.shahar91.poems.redux.reducers.ErrorReducer;
import com.shahar91.poems.redux.state.ViewStateReducer;
import com.shahar91.poems.ui.home.categories.redux.CategoryReducer;
import com.shahar91.poems.ui.home.poem.redux.PoemReducer;
import com.shahar91.poems.ui.home.poemsPerCategoryList.redux.PoemsPerCategoryListReducer;
import com.yheriatovych.reductor.Store;

import dagger.Module;
import dagger.Provides;

@Module
public class ReduxModule {
    public static AppStateReducer getAppStateReducer() {
        return AppStateReducer.builder()
                .viewStateReducer(ViewStateReducer.builder()
                        .categoryStateReducer(CategoryReducer.create())
                        .poemsPerCategoryListStateReducer(PoemsPerCategoryListReducer.create())
                        .poemStateReducer(PoemReducer.create())
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
