package com.shahar91.poems.ui.home.poem.adapter;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.airbnb.epoxy.Typed3EpoxyController;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PoemDetailAdapterController extends Typed2EpoxyController<Poem, Review> implements PoemNoReviewModel.Listener, PoemReviewModel.Listener {
    public interface Listener {
        void onRatingBarTouched(float rating);

        void onEditReviewClicked(Review review);
    }

    @AutoModel
    PoemDetailDataModel_ poemDetail;
    @AutoModel
    PoemNoReviewModel_ poemNoReviewModel;
    @AutoModel
    PoemReviewModel_ poemReviewModel;
    @AutoModel
    PoemGlobalRatingModel_ poemGlobalRatingModel;

    private final Context context;
    private final Listener listener;

    public PoemDetailAdapterController(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void buildModels(@Nonnull Poem poem, @Nullable Review ownReview) {
        poemDetail.poem(poem)
                .addTo(this);

        poemNoReviewModel
                .listener(this)
                .addIf(ownReview == null, this);

        if (ownReview != null) {
            poemReviewModel
                    .review(ownReview)
                    .listener(this)
                    .addTo(this);
        }

        if (!poem.getShortReviewList().isEmpty()) {
            poemGlobalRatingModel
                    .averageRating(poem.getAverageRating())
                    .totalRatingCount(poem.getTotalRatingCount())
                    .addTo(this);

            // TODO: only retrieve the 5 top reviews... create new screen to see a paged list of all reviews!!!
            for (Review review: poem.getShortReviewList()) {
                new PoemReviewModel_()
                        .id(review.getId())
                        .review(review)
                        .listener(this)
                        .addTo(this);
            }
        }
    }

    @Override
    public void onRatingBarTouched(float rating) {
        listener.onRatingBarTouched(rating);
    }

    @Override
    public void onEditReviewClicked(@NotNull Review review) {
        listener.onEditReviewClicked(review);
    }
}


























