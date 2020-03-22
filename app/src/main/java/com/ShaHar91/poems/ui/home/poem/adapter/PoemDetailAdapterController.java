package com.shahar91.poems.ui.home.poem.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed4EpoxyController;
import com.shahar91.poems.data.models.Poem;

import java.util.List;

public class PoemDetailAdapterController extends Typed4EpoxyController<Poem, String, String, List<String>> implements PoemNoReviewModel.Listener{
    public interface Listener {
        void onRatingBarTouched(float rating);

        void onOwnRatingClicked();
    }

    @AutoModel
    PoemDetailDataModel_ poemDetail;
    @AutoModel
    PoemNoReviewModel_ poemNoReviewModel;
//    @AutoModel
//    PoemOwnReviewModel_ poemOwnReviewModel;

    private final Context context;
    private final Listener listener;

    public PoemDetailAdapterController(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void buildModels(Poem poem, @Nullable String currentUserReview, String totalReviews, List<String> reviews) {
        poemDetail.poem(poem)
                .addTo(this);

        poemNoReviewModel.addIf(currentUserReview == null, this);

//        poemOwnReviewModel.review(currentUserReview).addIf(currentUserReview != null, this);
    }

    @Override
    public void onRatingBarTouched(float rating) {
        listener.onRatingBarTouched(rating);
    }
}
