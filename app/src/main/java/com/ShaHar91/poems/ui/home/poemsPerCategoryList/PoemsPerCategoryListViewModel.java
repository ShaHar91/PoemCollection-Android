package com.shahar91.poems.ui.home.poemsPerCategoryList;

import androidx.annotation.NonNull;

import com.shahar91.poems.data.DataManager;
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

import java.util.List;

import javax.inject.Inject;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import io.reactivex.Observable;
import kotlin.Unit;
import timber.log.Timber;

public class PoemsPerCategoryListViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    PoemsPerCategoryListViewModel(Store<AppState> store, DataManager dataManager) {
        super(store);
        this.dataManager = dataManager;
    }

    public void registerPoemsPerCategoryQuery(@NonNull String categoryId) {
        PoemRepository.getPoems(() -> {
            LoggingExtensionsKt.logd(null, "");
            return Unit.INSTANCE;
        }, throwable -> {
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });

//        addDisposable(dataManager.getPoemsPerCategories(categoryId).subscribe(poems -> {
//            PoemsPerCategoryListActions poemsPerCategoryListActions = Actions.from(PoemsPerCategoryListActions.class);
//            store.dispatch(poemsPerCategoryListActions.setPoemsPerCategoryList(poems));
//        }));
    }

    public Observable<List<Poem>> getPoemsPerCategory() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::poemsPerCategoryListState)
                .map(PoemsPerCategoryListState::poemsPerCategoryList)
                .distinctUntilChanged();
    }

    public void resetPoemsPerCategoryList() {
        PoemsPerCategoryListActions poemsPerCategoryListActions = Actions.from(PoemsPerCategoryListActions.class);
        store.dispatch(poemsPerCategoryListActions.reset());
    }
}
