package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.PoemBaseFragment
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener
import kotlinx.android.synthetic.main.fragment_poems_per_category.*


class PoemsPerCategoryListFragment :
    PoemBaseFragment<PoemsPerCategoryListViewModel>() {
    private val safeArgs: PoemsPerCategoryListFragmentArgs by navArgs()

    private lateinit var adapter: PoemsPerCategoryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poems_per_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            PoemsPerCategoryListViewModel::class.java)

        initViews()

        getAllPoemsForCategory()
    }

    private fun initViews() {
        adapter = PoemsPerCategoryListAdapter(requireActivity(), object : PoemsPerCategoryListInteractionListener {
            override fun onPoemClicked(poemId: String) {
                findNavController().navigate(PoemsPerCategoryListFragmentDirections.actionPoemsPerCategoryListFragmentToPoemFragment(poemId = poemId))
            }
        })
        adapter.setItems(viewModel.allPoemsForCategory)

        rvPoemsPerCategory.setupRecyclerView(null)
        rvPoemsPerCategory.adapter = adapter
        srlRefreshPoemsPerCategory.run {
            setOnRefreshListener(this@PoemsPerCategoryListFragment::getAllPoemsForCategory)
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllPoemsForCategory() {
        viewModel.getAllPoemsPerCategory(safeArgs.categoryId, {
            adapter.setItems(it)

            srlRefreshPoemsPerCategory.isRefreshing = false
        }, {
            it.printStackTrace()
            loge(null, it)

            srlRefreshPoemsPerCategory.isRefreshing = false
        })
    }
}