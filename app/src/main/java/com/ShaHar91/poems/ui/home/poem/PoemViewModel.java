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
    PoemActions poemActions = Actions.from(PoemActions.class);
    private String poemId;

    @Inject
    PoemViewModel(Store<AppState> store) {
        super(store);
    }

    public void init(String poemId) {
        this.poemId = poemId;
    }

    public Observable<PoemState> getPoem() {
        Observable<AppState> store = RxStore.asObservable(this.store);
        return store.map(AppState::viewState)
                .map(ViewState::poemState)
                .distinctUntilChanged();
    }

    public void getPoemAndAllReviews() {
        getPoemAndAllReviews(null);
    }

    public void getPoemAndAllReviews(Float rating) {
        getPoemFromBackend();

        if (HawkUtils.getHawkCurrentUserId() != null && !HawkUtils.getHawkCurrentUserId().isEmpty()) {
            ReviewRepository.getOwnReviewForPoem(this.poemId, review -> {
                store.dispatch(poemActions.setOwnReview(review));
                if (rating != null && rating > 0) {
                    store.dispatch(poemActions.setDelayedRating(rating));
                }
                return Unit.INSTANCE;
            }, throwable -> {
                LoggingExtensionsKt.loge(null, throwable, "");
                return Unit.INSTANCE;
            });
        }
    }

    private void getPoemFromBackend() {
        PoemRepository.getPoemById(this.poemId, poem -> {
            store.dispatch(poemActions.setPoem(poem));

            return Unit.INSTANCE;
        }, throwable -> {
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });
    }

    public void saveOrUpdateReview(String reviewId, String newReviewText, float newRating) {
        if (reviewId != null) {
            // Update review
            ReviewRepository.updateReview(poemId, reviewId, newReviewText, newRating, review -> {
                store.dispatch(poemActions.setOwnReview(review));

                getPoemFromBackend();

                return Unit.INSTANCE;
            }, throwable -> {
                LoggingExtensionsKt.loge(null, throwable, "");
                return Unit.INSTANCE;
            });
        } else {
            // new review
            ReviewRepository.createReview(poemId, newReviewText, newRating, review -> {
                store.dispatch(poemActions.setOwnReview(review));

                getPoemFromBackend();

                return Unit.INSTANCE;
            }, throwable -> {
                LoggingExtensionsKt.loge(null, throwable, "");
                return Unit.INSTANCE;
            });
        }
    }

    public void deleteReview(String reviewId) {
        ReviewRepository.deleteReview(reviewId, () -> {
            store.dispatch(poemActions.setOwnReview(null));

            getPoemFromBackend();

            return Unit.INSTANCE;
        }, throwable -> {
            LoggingExtensionsKt.loge(null, throwable, "");
            return Unit.INSTANCE;
        });
    }

    public void resetPoem() {
        store.dispatch(poemActions.reset());
    }

    public void resetRating() {
        store.dispatch(poemActions.setDelayedRating(null));
    }
}
