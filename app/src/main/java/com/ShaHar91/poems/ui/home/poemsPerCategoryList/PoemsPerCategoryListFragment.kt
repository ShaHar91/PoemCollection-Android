package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.emptyRecyclerView.RecyclerViewState
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentPoemsPerCategoryBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PoemsPerCategoryListFragment : PoemBaseBindingVMFragment<FragmentPoemsPerCategoryBinding>() {

    private val safeArgs: PoemsPerCategoryListFragmentArgs by navArgs()

    private val poemsPerCategoryListAdapter = PoemsPerCategoryListAdapter {
        PoemsPerCategoryListFragmentDirections.actionPoemsPerCategoryListFragmentToPoemFragment(it)
            .run(findNavController()::navigate)
    }

    override fun getLayout() = R.layout.fragment_poems_per_category
    override fun getToolbar() = mBinding.mergeToolbar.toolbar
    override val mViewModel: PoemsPerCategoryListViewModel by viewModel { parametersOf(safeArgs.categoryId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.viewModel = mViewModel

        initViews()
        initObservers()
    }

    private fun initViews() {
        mBinding.run {
            rvPoemsPerCategory.run {
                setupRecyclerView(null)
                emptyStateView = emptyView
                loadingStateView = loadingView
                adapter = poemsPerCategoryListAdapter
                state = RecyclerViewState.LOADING
            }

            themeSwipeToRefresh(srlRefreshPoemsPerCategory)
        }
    }

    private fun initObservers() {
        mViewModel.allPoemsForCategoryLive.observe(viewLifecycleOwner) {
            val poems = it.sortedBy { poem -> poem.title }

            mBinding.rvPoemsPerCategory.state = if (poems.isNotEmpty()) {
                poemsPerCategoryListAdapter.submitList(poems)
                RecyclerViewState.NORMAL
            } else {
                RecyclerViewState.EMPTY
            }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mBinding.run {
            if (rvPoemsPerCategory.state == RecyclerViewState.LOADING) {
                rvPoemsPerCategory.state = RecyclerViewState.EMPTY
            }
            mViewModel.setIsRefreshing(false)
        }
    }
}