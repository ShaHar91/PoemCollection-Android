package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoryFragment : PoemBaseFragment<CategoryViewModel>() {
    private lateinit var categoryAdapter: CategoryAdapter

    private val categoryAdapterListener = object : CategoryInteractionListener {
        override fun onCategoryClicked(category: Category) {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToPoemsPerCategoryListFragment(category._id,
                    category.name))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        initViews()

        getAllCategories(true)
    }

    private fun initViews() {
        categoryAdapter = CategoryAdapter(requireActivity(), categoryAdapterListener)

        rvCategories.apply {
            setupRecyclerView(null)
            emptyStateView = emptyView
            loadingStateView = loadingView
            adapter = categoryAdapter
        }

        srlRefreshCategories.apply {
            setOnRefreshListener { getAllCategories() }
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllCategories(showLoadingState: Boolean = false) {
        // only show the loading state at the start
        if (showLoadingState) rvCategories.stateView = RecyclerViewEnum.LOADING

        viewModel.getAllCategories({
            categoryAdapter.setItems(it)
            srlRefreshCategories.isRefreshing = false
        }, { throwable ->
            // when the response returns an error, show the data saved in the viewModel
            categoryAdapter.setItems(viewModel.categories)
            srlRefreshCategories.isRefreshing = false

            loge(null, throwable, "")
        })
    }
}