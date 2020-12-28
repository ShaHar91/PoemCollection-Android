package com.shahar91.poems.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.shahar91.poems.R
import kotlinx.android.synthetic.main.custom_circular_profile_image.view.*

// https://medium.com/@Zielony/guide-to-android-custom-views-constructors-df47476e334c
class CircularInitialsImageView : ConstraintLayout {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs ?: return

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.custom_circular_profile_image, this, true)
    }

    /**
     * Set the initials as a standard, just so that the view will still be filled
     * in case something happened to the image file (no connection, removed on server,...)
     *
     * @param url       The url for the image to load
     * @param initials  The text that will be loaded as an 'image'
     * @param color     The background color of the 'initialsView'
     */
    fun setImage(url: String?, initials: String, color: Int) {
        tvInitials.text = initials
        tvInitials.visibility = View.VISIBLE
        ivCircle.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_circular_image_color))
        ivCircle.imageTintList = ColorStateList.valueOf(color)

        if (url != null) {
            Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform()).listener(object :
                    RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                        isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                        dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        tvInitials.visibility = View.GONE
                        ivCircle.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(context, android.R.color.transparent))
                        return false
                    }
                }).into(ivImage)
        }
    }
}