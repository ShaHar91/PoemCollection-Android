package com.shahar91.poems.ui.add.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import be.appwise.core.extensions.libraries.copyFromRealm
import com.shahar91.poems.data.models.Category
import java.util.*

//TODO: https://medium.com/@droidbyme/autocomplete-textview-in-android-a1bf5fc112f6
// A category can be added multiple times, use some sort of tempList to remove
class CategoryAutoCompleteAdapter(context: Context, categories: Array<Category>) :
    ArrayAdapter<Category?>(context, R.layout.simple_list_item_1, ArrayList(categories.asList())) {

    // View lookup cache
    private class ViewHolder {
        var name: TextView? = null
    }

    override fun getView(position: Int, tempConvertView: View?, parent: ViewGroup): View {
        // Get the data item for this position
        var convertView: View? = tempConvertView
        val category: Category? = getItem(position)
        // Check if an existing view is being reused, otherwise inflate the view
        val viewHolder: ViewHolder // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.simple_list_item_1, parent, false)
            viewHolder.name = convertView?.findViewById(R.id.text1)

            // Cache the viewHolder object inside the fresh view
            convertView?.tag = viewHolder
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = convertView.tag as ViewHolder
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.name?.text = category?.name

        // Return the completed view to render on screen
        return convertView!!
    }

    override fun getFilter(): Filter {
        return categoryFilter
    }

    private val categoryFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            return if (charSequence != null) {
                val suggestions: MutableList<Category> = ArrayList<Category>()
                for (category in categories) {
                    if (category.name.toLowerCase(Locale.getDefault())
                            .startsWith(charSequence.toString().toLowerCase(Locale.getDefault()))
                    ) {
                        suggestions.add(category)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                filterResults
            } else {
                FilterResults()
            }
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
            if (filterResults != null && filterResults.count > 0) {
                clear()

                for (categoryObj in (filterResults.values as ArrayList<*>)) {
                    add(categoryObj as Category)
                }
                notifyDataSetChanged()
            } else {
                clear()
                notifyDataSetChanged()
            }
        }

    }
}