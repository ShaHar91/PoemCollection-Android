package com.shahar91.poems.ui.home.categories;

import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.state.ViewState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.categories.redux.CategoryActions;
import com.shahar91.poems.ui.home.categories.redux.CategoryState;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;
import com.yheriatovych.reductor.rxjava2.RxStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CategoryViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    public CategoryViewModel(Store<AppState> store,
                             DataManager dataManager) {
        super(store);
        this.dataManager = dataManager;
    }

    public void registerCategoryQuery() {
        addDisposable(dataManager.getCategories()
                .subscribe(categories -> {
                    CategoryActions categoryActions = Actions.from(CategoryActions.class);
                    store.dispatch(categoryActions.setCategoryList(categories));
                }));
    }

    public Observable<List<Category>> getCategories() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::categoryState)
                .map(CategoryState::categoryList)
                .distinctUntilChanged();
    }
}
