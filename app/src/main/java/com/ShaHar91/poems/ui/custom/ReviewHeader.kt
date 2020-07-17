package com.shahar91.poems.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.shahar91.poems.R
import kotlinx.android.synthetic.main.custom_review_header.view.*

class ReviewHeader(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var view: View

    var userName: String = ""
        set(value) {
            field = value
            view.tvUsername.text = value
        }

    var reviewTimestamp: Long = 0
        set(value) {
            field = value
            view.tvReviewTimestamp.text = "$value"
        }

    var rating: Float = 0f
        set(value) {
            field = value
            view.rbOwnRating.rating = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_review_header, this, true)
    }

    fun setImage(url: String?, initials: String, color: Int) {
        ivUserImage.setImage(url, initials, color)
    }
}