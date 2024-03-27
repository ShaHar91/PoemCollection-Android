package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.emptyRecyclerView.RecyclerViewState
import com.shahar91.poems.MyApp
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentCategoriesBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter

class CategoryFragment : PoemBaseBindingVMFragment<FragmentCategoriesBinding>() {

    private val categoryAdapter = CategoryAdapter {
        CategoryFragmentDirections.actionCategoryFragmentToPoemsPerCategoryListFragment(it.id, it.name)
            .run(findNavController()::navigate)
    }

    override fun getLayout() = R.layout.fragment_categories
    override fun getToolbar() = mBinding.mergeToolbar.toolbar
    override val mViewModel: CategoryViewModel by viewModels { getViewModelFactory() }
    override fun getViewModelFactory() = CategoryViewModel.FACTORY(MyApp.categoryRepository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.run {
            viewModel = mViewModel.apply {
                getAllCategories()
            }
        }

        initViews()
        initObservers()
    }

    private fun initViews() {
        mBinding.run {
            rvCategories.run {
                setupRecyclerView(null)
                emptyStateView = emptyView
                loadingStateView = loadingView
                adapter = categoryAdapter
                state = RecyclerViewState.LOADING
            }

            themeSwipeToRefresh(srlRefreshCategories)
        }
    }

    private fun initObservers() {
        mViewModel.categoriesLive.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
            if (it.isNotEmpty()) {
                mBinding.rvCategories.state = RecyclerViewState.NORMAL
            }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mBinding.run {
            if (rvCategories.state == RecyclerViewState.LOADING) {
                rvCategories.state = RecyclerViewState.EMPTY
            }
            mViewModel.setIsRefreshing(false)
        }
    }
}