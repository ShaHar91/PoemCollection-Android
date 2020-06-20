package com.shahar91.poems.ui.home.poemsPerCategoryList;

import androidx.annotation.NonNull;

import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.repositories.PoemRepository;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.state.ViewState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.poemsPerCategoryList.redux.PoemsPerCategoryListActions;
import com.shahar91.poems.ui.home.poemsPerCategoryList.redux.PoemsPerCategoryListState;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;
import com.yheriatovych.reductor.rxjava2.RxStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import io.reactivex.Observable;
import kotlin.Unit;

public class PoemsPerCategoryListViewModel extends BaseGoogleViewModel {

    @Inject
    PoemsPerCategoryListViewModel(Store<AppState> store) {
        super(store);
    }

    public void getAllPoemsPerCategory(@NonNull String categoryId) {
        PoemRepository.getPoems(categoryId, poems -> {
            PoemsPerCategoryListActions poemsPerCategoryListActions = Actions.from(PoemsPerCategoryListActions.class);
            store.dispatch(poemsPerCategoryListActions.setPoemsPerCategoryList(new ArrayList<>(poems)));

            return Unit.INSTANCE;
        }, throwable -> {
            throwable.printStackTrace();
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });
    }

    public Observable<List<Poem>> poemsPerCategoryStateListener() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::poemsPerCategoryListState)
                .map(PoemsPerCategoryListState::poemsPerCategoryList)
                .distinctUntilChanged();
    }

    /**
     * Don't exactly know why this was needed
     * Could be disable to test certain conditions
     */
    public void resetPoemsPerCategoryList() {
        PoemsPerCategoryListActions poemsPerCategoryListActions = Actions.from(PoemsPerCategoryListActions.class);
        store.dispatch(poemsPerCategoryListActions.reset());
    }
}
