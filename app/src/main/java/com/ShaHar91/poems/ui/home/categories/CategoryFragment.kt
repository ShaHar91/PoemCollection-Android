package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.base.BaseBindingVMFragment
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentCategoriesBinding
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter

class CategoryFragment : BaseBindingVMFragment<FragmentCategoriesBinding>() {

    private val categoryAdapter = CategoryAdapter {
        findNavController()
                .navigate(CategoryFragmentDirections.actionCategoryFragmentToPoemsPerCategoryListFragment(it.id, it.name))
    }

    override fun getLayout() = R.layout.fragment_categories
    override val mViewModel: CategoryViewModel by viewModels()

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
                stateView = RecyclerViewEnum.LOADING
            }

            srlRefreshCategories.run {
                setColorSchemeResources(R.color.colorWhite)
                setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
            }
        }
    }

    private fun initObservers() {
        mViewModel.categoriesLive.observe(viewLifecycleOwner, {
            categoryAdapter.submitList(it)
            if (it.isNotEmpty()) {
                mBinding.rvCategories.stateView = RecyclerViewEnum.NORMAL
            }
        })
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mBinding.run {
            if (rvCategories.stateView == RecyclerViewEnum.LOADING) {
                rvCategories.stateView = RecyclerViewEnum.EMPTY_STATE
            }
            mViewModel.setIsRefreshing(false)
        }
    }
}