package com.shahar91.poems.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RatingBar
import com.google.android.material.card.MaterialCardView
import com.shahar91.poems.R
import kotlinx.android.synthetic.main.custom_no_review.view.*

class NoReview@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialCardView(context, attrs, defStyleAttr) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_no_review, this, true)
    }

    fun setOnRatingChangedListener(callback: (ratingBar: RatingBar, rating: Float, fromUser: Boolean)->Unit) {
        rbNoReview.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->  callback(ratingBar, rating, fromUser)}
    }
}