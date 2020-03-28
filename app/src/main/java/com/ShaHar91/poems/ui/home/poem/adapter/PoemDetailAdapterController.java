package com.shahar91.poems.ui.home.poem.adapter;

import android.content.Context;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.shahar91.poems.Constants;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;

import javax.annotation.Nonnull;

public class PoemDetailAdapterController extends TypedEpoxyController<Poem> implements PoemNoReviewModel.Listener, PoemOwnReviewModel.Listener {
    public interface Listener {
        void onRatingBarTouched(float rating);

        void onOwnReviewClicked(String review);
    }

    @AutoModel
    PoemDetailDataModel_ poemDetail;
    @AutoModel
    PoemNoReviewModel_ poemNoReviewModel;
    @AutoModel
    PoemOwnReviewModel_ poemOwnReviewModel;

    private final Context context;
    private final Listener listener;

    public PoemDetailAdapterController(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    //TODO: Nu.. In uw geval gaat ExpressJS (NodeJS Express) + MongoDB in combinatie met MongoDB Atlas wel het makkelijkste en snelste zijn. Met MongoDB kunt ge ook een goeie ORM gebruiken. Zeker doen voor XSS attacks :slightly_smiling_face:
    // Ik gebruik Sequelize, maar MongoDB heeft andere goeie ook :slightly_smiling_face:


    // TODO: not all parameters are needed, they are all inside of the poem object, just extract them and add the correct views to the list!!!
    @Override
    protected void buildModels(@Nonnull Poem poem) {
        poemDetail.poem(poem)
                .addTo(this);

        Review review = poem.getReviews().where().equalTo("user.userId", Constants.CURRENT_USER_ID).findFirst();

        poemNoReviewModel.listener(this).addIf(review == null, this);

        if (review != null) {
            poemOwnReviewModel.review(review).listener(this).addTo(this);
        }
    }

    @Override
    public void onRatingBarTouched(float rating) {
        listener.onRatingBarTouched(rating);
    }

    @Override
    public void onOwnReviewClicked(String review) {
        listener.onOwnReviewClicked(review);
    }
}
