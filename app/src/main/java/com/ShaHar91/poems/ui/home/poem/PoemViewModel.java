package com.shahar91.poems.ui.home.poem;

import com.shahar91.poems.data.repositories.PoemRepository;
import com.shahar91.poems.data.repositories.ReviewRepository;
import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.redux.state.ViewState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.shahar91.poems.ui.home.poem.redux.PoemActions;
import com.shahar91.poems.ui.home.poem.redux.PoemState;
import com.shahar91.poems.utils.HawkUtils;
import com.yheriatovych.reductor.Actions;
import com.yheriatovych.reductor.Store;
import com.yheriatovych.reductor.rxjava2.RxStore;

import javax.inject.Inject;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import io.reactivex.Observable;
import kotlin.Unit;

public class PoemViewModel extends BaseGoogleViewModel {
    @Inject
    PoemViewModel(Store<AppState> store) {
        super(store);
    }

    public void getPoemAndAllReviews(String poemId) {
        PoemRepository.getPoemById(poemId, HawkUtils.getHawkCurrentUserId(), poem -> {
            PoemActions poemActions = Actions.from(PoemActions.class);
            store.dispatch(poemActions.setPoem(poem));

            return Unit.INSTANCE;
        }, throwable -> {
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });

        if (HawkUtils.getHawkCurrentUserId() != null && !HawkUtils.getHawkCurrentUserId().isEmpty()) {
            ReviewRepository.getOwnReviewForPoem(poemId, HawkUtils.getHawkCurrentUserId(), review -> {
                PoemActions poemActions = Actions.from(PoemActions.class);
                store.dispatch(poemActions.setOwnReview(review));

                return Unit.INSTANCE;
            }, throwable -> {
                LoggingExtensionsKt.loge(null, throwable, "");
                return Unit.INSTANCE;
            });
        }
    }

    public Observable<PoemState> getPoem() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::poemState)
                .distinctUntilChanged();
    }

    public void resetPoem() {
        PoemActions poemActions = Actions.from(PoemActions.class);
        store.dispatch(poemActions.reset());
    }
}
