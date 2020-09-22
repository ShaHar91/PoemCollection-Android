package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoryFragment : PoemBaseFragment<CategoryViewModel>() {
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        initViews()

        getAllCategories()
    }

    private fun initViews() {
        adapter = CategoryAdapter(requireActivity(), object : CategoryInteractionListener {
            override fun onCategoryClicked(category: Category) {
                findNavController().navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToPoemsPerCategoryListFragment(category._id,
                        category.name))
            }
        })
        adapter.setItems(viewModel.categories)

        rvCategories.setupRecyclerView(null)
        rvCategories.adapter = adapter
        srlRefreshCategories.run {
            setOnRefreshListener(this@CategoryFragment::getAllCategories)
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllCategories() {
        viewModel.getAllCategories({
            adapter.setItems(it)

            srlRefreshCategories.isRefreshing = false
        }, { throwable ->
            throwable.printStackTrace()
            loge(null, throwable, "")

            srlRefreshCategories.isRefreshing = false
        })
    }
}