package com.shahar91.poems.ui.home.poemsPerCategoryList;

import com.google.firebase.firestore.ListenerRegistration;
import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.data.models.PoemsPerCategory;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.poemsPerCategoryList.redux.PoemsPerCategoryListActions;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;

import java.util.List;

import javax.inject.Inject;

public class PoemsPerCategoryListViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    public PoemsPerCategoryListViewModel(Store<AppState> store, DataManager dataManager) {
        super(store);
        this.dataManager = dataManager;
    }

    public void registerPoemsPerCategoryQuery(int categoryId) {
        registration = dataManager.getPoemsPerCategoryByReference(categoryId).addSnapshotListener(((querySnapshots, exception) -> {
            PoemsPerCategoryListActions poemsPerCategoryListActions = Actions.from(PoemsPerCategoryListActions.class);
            store.dispatch(poemsPerCategoryListActions.setPoemsPerCategoryList(querySnapshots.toObjects(PoemsPerCategory.class)));
        }));
    }
}
