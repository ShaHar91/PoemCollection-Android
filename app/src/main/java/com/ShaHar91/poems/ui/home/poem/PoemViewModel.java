package com.shahar91.poems.ui.home.poem;

import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.state.ViewState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.poem.redux.PoemActions;
import com.shahar91.poems.ui.home.poem.redux.PoemState;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;
import com.yheriatovych.reductor.rxjava2.RxStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PoemViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    public PoemViewModel(Store<AppState> store, DataManager dataManager) {
        super(store);
        this.dataManager = dataManager;
    }

    public void registerPoemQuery(String poemId) {
        registration = dataManager.getPoemByReference(poemId).addSnapshotListener(((querySnapshots, exception) -> {
            PoemActions poemActions = Actions.from(PoemActions.class);
            store.dispatch(poemActions.setPoem(querySnapshots.toObject(Poem.class)));
        }));
    }

    public Observable<Poem> getPoem() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::poemState)
                .map(PoemState::poem)
                .distinctUntilChanged();
    }

    public void resetPoem() {
        PoemActions poemActions = Actions.from(PoemActions.class);
        store.dispatch(poemActions.reset());
    }
}
