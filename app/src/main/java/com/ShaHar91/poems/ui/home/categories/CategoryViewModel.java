package com.shahar91.poems.ui.home.categories;

import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.repositories.CategoryRepository;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.state.ViewState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.categories.redux.CategoryActions;
import com.shahar91.poems.ui.home.categories.redux.CategoryState;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;
import com.yheriatovych.reductor.rxjava2.RxStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import io.reactivex.Observable;
import kotlin.Unit;

public class CategoryViewModel extends BaseGoogleViewModel {

    @Inject
    CategoryViewModel(Store<AppState> store) {
        super(store);
    }

    public void getAllCategories() {
        CategoryRepository.getCategories(categories -> {
            CategoryActions categoryActions = Actions.from(CategoryActions.class);
            store.dispatch(categoryActions.setCategoryList(new ArrayList<>(categories)));

            return Unit.INSTANCE;
        }, throwable -> {
            throwable.printStackTrace();
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });
    }

    public Observable<List<Category>> categoriesStateListener() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::categoryState)
                .filter(categoryState -> categoryState.categoryList() != null)
                .map(CategoryState::categoryList)
                .distinctUntilChanged();
    }
}
