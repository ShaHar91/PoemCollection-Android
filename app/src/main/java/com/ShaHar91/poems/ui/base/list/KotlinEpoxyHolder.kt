package com.shahar91.poems.ui.base.list

import android.view.View
import com.airbnb.epoxy.EpoxyHolder

/**
 * A pattern for easier view binding with an [EpoxyHolder]
 *
 * This pattern was throwing the Exception a lot what seemed to be without a proper reason
 * Disabled the 'bind' function for now and also commented the 'abstract' class
 *
 * See [com.airbnb.epoxy.kotlinsample.models.ItemEpoxyHolder] for a usage example.
 */
/*abstract*/ class KotlinEpoxyHolder : EpoxyHolder() {
    public lateinit var view: View

    override fun bindView(itemView: View) {
        view = itemView
    }

    //    protected fun <V : View> bind(id: Int): ReadOnlyProperty<KotlinEpoxyHolder, V> =
    //        Lazy { holder: KotlinEpoxyHolder, prop ->
    //            holder.view.findViewById(id) as V?
    //                ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
    //        }
    //
    //    /**
    //     * Taken from Kotterknife.
    //     * https://github.com/JakeWharton/kotterknife
    //     */
    //    private class Lazy<V>(
    //        private val initializer: (KotlinEpoxyHolder, KProperty<*>) -> V
    //    ) : ReadOnlyProperty<KotlinEpoxyHolder, V> {
    //        private object EMPTY
    //
    //        private var value: Any? = EMPTY
    //
    //        override fun getValue(thisRef: KotlinEpoxyHolder, property: KProperty<*>): V {
    //            if (value == EMPTY) {
    //                value = initializer(thisRef, property)
    //            }
    //            @Suppress("UNCHECKED_CAST")
    //            return value as V
    //        }
    //    }
}