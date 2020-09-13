package com.shahar91.poems.ui.home.categories

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.BaseActivity
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment
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

        addDisposable(viewModel.categoriesStateListener().subscribe({
            adapter.items = it
        }, Throwable::printStackTrace))
    }

    private fun initViews() {
        toolbar.title = getString(R.string.categories_toolbar_title)
        configureToolbar(toolbar, null)
        adapter = CategoryAdapter(requireActivity(),
            CategoryInteractionListener { category: Category -> handleClick(category) })
        rvCategories.layoutManager = LinearLayoutManager(requireActivity())
        rvCategories.adapter = adapter
        srlRefreshCategories.setOnRefreshListener {
            viewModel.getAllCategories()
            Handler().postDelayed({ srlRefreshCategories.isRefreshing = false },
                Constants.DEFAULT_REFRESH_LAYOUT_DURATION)
        }
    }

    private fun handleClick(category: Category) {
        val poemsPerCategoryListFragment = PoemsPerCategoryListFragment.newInstance(true,
            category)
        (requireActivity() as BaseActivity).replaceFragment(R.id.flHomeContainer,
            poemsPerCategoryListFragment, TAG_POEMS_LIST, true)
    }
}