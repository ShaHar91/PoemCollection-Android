package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.base.BaseVMFragment
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentPoemsPerCategoryBinding
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener

class PoemsPerCategoryListFragment :
    BaseVMFragment<PoemsPerCategoryListViewModel>() {
    private val safeArgs: PoemsPerCategoryListFragmentArgs by navArgs()
    private lateinit var mDataBinding: FragmentPoemsPerCategoryBinding
    private lateinit var poemsPerCategoryListAdapter: PoemsPerCategoryListAdapter

    private val poemsPerCategoryListAdapterListener =
        object : PoemsPerCategoryListInteractionListener {
            override fun onPoemClicked(poemId: String) {
                PoemsPerCategoryListFragmentDirections.actionPoemsPerCategoryListFragmentToPoemFragment(poemId)
                    .run(findNavController()::navigate)
            }
        }

    override fun getViewModel() = PoemsPerCategoryListViewModel::class.java

    override fun getViewModelFactory(): ViewModelProvider.NewInstanceFactory {
        return PoemsPerCategoryListViewModel.FACTORY(safeArgs.categoryId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        mDataBinding = DataBindingUtil.inflate<FragmentPoemsPerCategoryBinding>(inflater,
            R.layout.fragment_poems_per_category, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = mViewModel.apply {
                    setDefaultExceptionHandler(::onError)
                    getAllPoemsForCategoryCr(safeArgs.categoryId)
                }
            }

        initViews()

        return mDataBinding.root
    }

    private fun initViews() {
        poemsPerCategoryListAdapter = PoemsPerCategoryListAdapter(poemsPerCategoryListAdapterListener)

        mDataBinding.apply {
            rvPoemsPerCategory.apply {
                setupRecyclerView(null)
                emptyStateView = emptyView
                loadingStateView = loadingView
                adapter = poemsPerCategoryListAdapter
                stateView = RecyclerViewEnum.LOADING
            }

            srlRefreshPoemsPerCategory.run {
                setOnRefreshListener { viewModel?.getAllPoemsForCategoryCr(safeArgs.categoryId) }
                setColorSchemeResources(R.color.colorWhite)
                setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
            }

            viewModel?.allPoemsForCategoryLive?.observe(viewLifecycleOwner, Observer {
                srlRefreshPoemsPerCategory.isRefreshing = false
                poemsPerCategoryListAdapter.setItems(it)
            })
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        mDataBinding.apply {
            if (rvPoemsPerCategory.stateView == RecyclerViewEnum.LOADING) {
                rvPoemsPerCategory.stateView = RecyclerViewEnum.EMPTY_STATE
            }
            srlRefreshPoemsPerCategory.isRefreshing = false
        }
    }
}