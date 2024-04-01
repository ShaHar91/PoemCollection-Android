package com.shahar91.poems.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.shahar91.poems.R
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class ReviewHeader(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private val tvUsername by lazy {
        findViewById<TextView>(R.id.tvUsername)
    }

    private val tvReviewTimestamp by lazy {
        findViewById<TextView>(R.id.tvReviewTimestamp)
    }

    private val rbOwnRating by lazy {
        findViewById<MaterialRatingBar>(R.id.rbOwnRating)
    }

    private val ivUserImage by lazy {
        findViewById<CircularInitialsImageView>(R.id.ivUserImage)
    }

    private var view: View

    var userName: String? = ""
        set(value) {
            field = value
            tvUsername.text = value
            value?.let {
                setImage(imageUrl, it[0].toString())
            }
        }

    var reviewTimestamp: String? = ""
        set(value) {
            field = value
            tvReviewTimestamp.text = "$value"
        }

    var rating: Float? = 0f
        set(value) {
            field = value
            value?.let {
                rbOwnRating.rating = it
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

    fun setImage(
        url: String? = null,
        initials: String = "",
        color: Int = ContextCompat.getColor(context, R.color.colorPrimary)
    ) {
        ivUserImage.setImage(url, initials, color)
    }
}