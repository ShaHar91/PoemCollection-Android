package com.shahar91.poems.ui.home.poem.adapter

import be.appwise.core.extensions.logging.logd
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.list_item_poem_detail.view.*
import timber.log.Timber

@EpoxyModelClass(layout = R.layout.list_item_poem_detail)
abstract class PoemDetailDataModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    @EpoxyAttribute
    lateinit var poem: Poem

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.apply {
            logd("POEM_SOMETHING", poem.toString())
            tvPoemTitle.text = poem.title
            tvPoem.text = poem.body
            tvWriterPoemDetail.text = poem.user.username
        }
    }
}
