package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.base.BaseBindingVMFragment
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentPoemsPerCategoryBinding
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter

class PoemsPerCategoryListFragment :
    BaseBindingVMFragment<PoemsPerCategoryListViewModel, FragmentPoemsPerCategoryBinding>() {
    private val safeArgs: PoemsPerCategoryListFragmentArgs by navArgs()

    private val poemsPerCategoryListAdapter = PoemsPerCategoryListAdapter {
        //                PoemsPerCategoryListFragmentDirections.actionPoemsPerCategoryListFragmentToPoemFragment(poemId)
        //                    .run(findNavController()::navigate)
        mViewModel.getPoemsWithRelations(it)
    }

    override fun getLayout() = R.layout.fragment_poems_per_category
    override fun getViewModel() = PoemsPerCategoryListViewModel::class.java
    override fun getViewModelFactory() = PoemsPerCategoryListViewModel.FACTORY(safeArgs.categoryId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel.apply {
                getAllPoemsForCategoryId()
            }
        }

        initViews()
        initObservers()
    }

    private fun initViews() {
        mBinding.apply {
            rvPoemsPerCategory.apply {
                setupRecyclerView(null)
                emptyStateView = emptyView
                loadingStateView = loadingView
                adapter = poemsPerCategoryListAdapter
                stateView = RecyclerViewEnum.LOADING
            }

            srlRefreshPoemsPerCategory.run {
                setColorSchemeResources(R.color.colorWhite)
                setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
            }
        }
    }

    private fun initObservers() {
        mViewModel.allPoemsForCategoryLive.observe(viewLifecycleOwner, {
            val poems = it.poems

            poemsPerCategoryListAdapter.submitList(poems)
            if (poems.isNotEmpty()) {
                mBinding.rvPoemsPerCategory.stateView = RecyclerViewEnum.NORMAL
            }
        })
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mBinding.apply {
            if (rvPoemsPerCategory.stateView == RecyclerViewEnum.LOADING) {
                rvPoemsPerCategory.stateView = RecyclerViewEnum.EMPTY_STATE
            }
            mViewModel.setIsRefreshing(false)
        }
    }
}