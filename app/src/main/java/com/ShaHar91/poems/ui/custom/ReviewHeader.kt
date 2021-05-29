package com.shahar91.poems.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.shahar91.poems.R
import kotlinx.android.synthetic.main.custom_review_header.view.*

class ReviewHeader(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var view: View

    var userName: String? = ""
        set(value) {
            field = value
            view.tvUsername.text = value
            value?.let {
                setImage(imageUrl, it[0].toString())
            }
        }

    var reviewTimestamp: String? = ""
        set(value) {
            field = value
            view.tvReviewTimestamp.text = "$value"
        }

    var rating: Float? = 0f
        set(value) {
            field = value
            value?.let {
                view.rbOwnRating.rating = it
            }
        }

    var imageUrl: String? = ""
        set(value) {
            field = value
            setImage(value, userName?.get(0).toString())
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_review_header, this, true)
    }

    fun setImage(url: String? = null, initials: String = "", color: Int = ContextCompat.getColor(context, R.color.colorPrimary)) {
        ivUserImage.setImage(url, initials, color)
    }
}