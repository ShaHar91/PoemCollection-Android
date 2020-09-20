package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.logging.loge
import be.appwise.core.extensions.view.setupRecyclerView
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.BaseGoogleFragment
import com.shahar91.poems.ui.base.BaseGoogleMobileActivity
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener
import com.shahar91.poems.ui.home.poemsPerCategoryList.PoemsPerCategoryListFragment
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.toolbar.*

class CategoryFragment : BaseGoogleFragment<CategoryViewModel, CategoryComponent>() {
    companion object {
        private const val TAG_POEMS_LIST = "TagPoemsList"

        @JvmStatic
        fun newInstance(showBackIcon: Boolean): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putBoolean(SHOW_BACK_ICON, showBackIcon)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapter: CategoryAdapter

    override fun createComponent(): CategoryComponent {
        return DaggerCategoryComponent.builder()
            .applicationComponent(appComponent())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CategoryViewModel::class.java)

        initViews()

        getAllCategories()
    }

    private fun initViews() {
        toolbar.apply {
            title = getString(R.string.categories_toolbar_title)
            configureToolbar(this, null)
        }

        adapter = CategoryAdapter(requireActivity(), object : CategoryInteractionListener {
            override fun onCategoryClicked(category: Category) {
                handleClick(category)
            }
        })
        adapter.setItems(viewModel.categories)

        rvCategories.setupRecyclerView(null)
        rvCategories.adapter = adapter
        srlRefreshCategories.run {
            setOnRefreshListener(this@CategoryFragment::getAllCategories)
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    private fun getAllCategories() {
        viewModel.getAllCategories({
            adapter.setItems(it)

            srlRefreshCategories.isRefreshing = false
        }, { throwable ->
            throwable.printStackTrace()
            loge(null, throwable, "")

            srlRefreshCategories.isRefreshing = false
        })
    }

    private fun handleClick(category: Category) {
        val fragment = PoemsPerCategoryListFragment.newInstance(true, category)
        (requireActivity() as BaseGoogleMobileActivity<*, *>).replaceFragment(R.id.flHomeContainer, fragment,
            TAG_POEMS_LIST, true)
    }
}