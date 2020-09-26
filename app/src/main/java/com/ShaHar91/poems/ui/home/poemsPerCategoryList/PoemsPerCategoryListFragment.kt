package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import be.appwise.core.ui.custom.RecyclerViewEnum
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener
import kotlinx.android.synthetic.main.fragment_poems_per_category.*
import kotlinx.android.synthetic.main.fragment_poems_per_category.emptyView
import kotlinx.android.synthetic.main.fragment_poems_per_category.loadingView

class PoemsPerCategoryListFragment :
    PoemBaseFragment<PoemsPerCategoryListViewModel>() {
    private val safeArgs: PoemsPerCategoryListFragmentArgs by navArgs()

    private lateinit var poemsPerCategoryListAdapter: PoemsPerCategoryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_poems_per_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            PoemsPerCategoryListViewModel::class.java)

        initViews()

        getAllPoemsForCategory(true)
    }

    private fun initViews() {
        poemsPerCategoryListAdapter = PoemsPerCategoryListAdapter(requireActivity(),
            object : PoemsPerCategoryListInteractionListener {
                override fun onPoemClicked(poemId: String) {
                    findNavController().navigate(
                        PoemsPerCategoryListFragmentDirections.actionPoemsPerCategoryListFragmentToPoemFragment(
                            poemId = poemId))
                }
            })

        rvPoemsPerCategory.apply {
            setupRecyclerView(null)
            emptyStateView = emptyView
            loadingStateView = loadingView
            adapter = poemsPerCategoryListAdapter
        }

        srlRefreshPoemsPerCategory.run {
            setOnRefreshListener { getAllPoemsForCategory() }
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllPoemsForCategory(showLoadingState: Boolean = false) {
        // only show the loading state at the start
        if (showLoadingState) rvPoemsPerCategory.stateView = RecyclerViewEnum.LOADING

        viewModel.getAllPoemsPerCategory(safeArgs.categoryId, {
            poemsPerCategoryListAdapter.setItems(it)

            srlRefreshPoemsPerCategory.isRefreshing = false
        }, {
            loge(null, it)

            srlRefreshPoemsPerCategory.isRefreshing = false
        })
    }
}