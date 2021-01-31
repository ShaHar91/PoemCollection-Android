package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.base.BaseBindingVMFragment
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.databinding.FragmentCategoriesBinding
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener

class CategoryFragment : BaseBindingVMFragment<CategoryViewModel, FragmentCategoriesBinding>() {
    private lateinit var categoryAdapter: CategoryAdapter

    private val categoryAdapterListener = object : CategoryInteractionListener {
        override fun onCategoryClicked(category: Category) {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToPoemsPerCategoryListFragment(category._id,
                    category.name))
        }
    }

    override fun getLayout() = R.layout.fragment_categories

    override fun getViewModel() = CategoryViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel.apply {
                setDefaultExceptionHandler(::onError)
                getAllCategoriesCr()
            }
        }

        initViews()
    }

    private fun initViews() {
        categoryAdapter = CategoryAdapter(categoryAdapterListener)

        mBinding.apply {
            rvCategories.apply {
                setupRecyclerView(null)
                emptyStateView = emptyView
                loadingStateView = loadingView
                adapter = categoryAdapter
                stateView = RecyclerViewEnum.LOADING
            }

            srlRefreshCategories.apply {
                setOnRefreshListener { viewModel?.getAllCategoriesCr() }
                setColorSchemeResources(R.color.colorWhite)
                setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
            }

            viewModel?.categoriesLive?.observe(viewLifecycleOwner, Observer {
                srlRefreshCategories.isRefreshing = false
                categoryAdapter.setItems(it)
            })
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mBinding.apply {
            if (rvCategories.stateView == RecyclerViewEnum.LOADING) {
                rvCategories.stateView = RecyclerViewEnum.EMPTY_STATE
            }
            srlRefreshCategories.isRefreshing = false
        }
    }
}