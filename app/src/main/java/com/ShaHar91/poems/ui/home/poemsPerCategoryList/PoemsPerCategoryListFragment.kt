package com.shahar91.poems.ui.home.poemsPerCategoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import com.shahar91.poems.MyApp
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.BaseActivity
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment
import com.shahar91.poems.ui.home.poem.PoemFragment
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener
import kotlinx.android.synthetic.main.fragment_poems_per_category.*
import kotlinx.android.synthetic.main.toolbar.*

class PoemsPerCategoryListFragment :
    BaseGoogleFragment<PoemsPerCategoryListViewModel, PoemsPerCategoryListComponent>() {

    companion object {
        private const val TAG_POEM = "TagPoem"
        private const val CATEGORY_ID = "CATEGORY_ID"
        private const val CATEGORY_NAME = "CATEGORY_NAME"
        fun newInstance(showBackIcon: Boolean,
            category: Category): PoemsPerCategoryListFragment {
            val fragment = PoemsPerCategoryListFragment()
            val args = Bundle()
            args.putBoolean(SHOW_BACK_ICON, showBackIcon)
            args.putString(CATEGORY_ID, category.id)
            args.putString(CATEGORY_NAME, category.name)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapter: PoemsPerCategoryListAdapter

    override fun createComponent(): PoemsPerCategoryListComponent {
        return DaggerPoemsPerCategoryListComponent.builder()
            .applicationComponent((requireActivity().application as MyApp).appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poems_per_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(
            PoemsPerCategoryListViewModel::class.java)

        initViews()

        getAllPoemsForCategory()
    }

    private fun initViews() {
        toolbar.apply {
            title = requireArguments().getString(CATEGORY_NAME, "")
            configureToolbar(this, ContextCompat.getColor(requireActivity(), R.color.colorWhite))
        }
        adapter = PoemsPerCategoryListAdapter(requireActivity(),
            PoemsPerCategoryListInteractionListener { poemId: String -> handleClick(poemId) })
        adapter.items = viewModel.allPoemsForCategory

        rvPoemsPerCategory.setupRecyclerView(null)
        rvPoemsPerCategory.adapter = adapter
        srlRefreshPoemsPerCategory.run {
            setOnRefreshListener(this@PoemsPerCategoryListFragment::getAllPoemsForCategory)
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllPoemsForCategory() {
        viewModel.getAllPoemsPerCategory(requireArguments().getString(CATEGORY_ID, ""), {
            adapter.items = it

            srlRefreshPoemsPerCategory.isRefreshing = false
        }, {
            it.printStackTrace()
            loge(null, it)

            srlRefreshPoemsPerCategory.isRefreshing = false
        })
    }

    private fun handleClick(poemId: String) {
        val poemFragment = PoemFragment.newInstance(true, poemId)
        (requireActivity() as BaseActivity).replaceFragment(R.id.flHomeContainer, poemFragment, TAG_POEM, true)
    }
}