package com.shahar91.poems.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.logging.logd
import be.appwise.core.networking.Networking.isLoggedIn
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.ui.add.AddPoemActivity.Companion.startWithIntent
import com.shahar91.poems.ui.base.BaseGoogleMobileActivity
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.home.categories.CategoryFragment
import com.shahar91.poems.ui.home.categories.CategoryFragment.Companion.newInstance
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseGoogleMobileActivity<HomeViewModel, HomeComponent>() {
    private var categoryFragment: CategoryFragment = newInstance(false)

    companion object {
        private const val TAG_CATEGORIES = "TagCategories"
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun createComponent(): HomeComponent {
        return DaggerHomeComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        showCategoryFragment(savedInstanceState)

        fabAddPoem.setOnClickListener {
            if (isLoggedIn()) {
                startAddPoem()
            } else {
                // start the EntryActivity to make sure the user gets logged in
                startActivityForResult(EntryActivity.startWithIntent(this),
                    Constants.REQUEST_CODE_NEW_USER)
            }
        }
    }

    private fun showCategoryFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            replaceFragment(R.id.flHomeContainer, categoryFragment, TAG_CATEGORIES, false)
        } else {
            logd("FragmentByTag", "Find Category Fragment By Tag")
            categoryFragment = supportFragmentManager.findFragmentByTag(
                TAG_CATEGORIES) as CategoryFragment
        }
    }

    /**
     * This may only be called when the user successfully logged in or already was logged in
     */
    private fun startAddPoem() {
        startActivityForResult(startWithIntent(this),
            Constants.REQUEST_CODE_ADD_POEM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constants.REQUEST_CODE_NEW_USER -> {
                if (resultCode == Activity.RESULT_OK) {
                    // A user has been logged in successfully
                    startAddPoem()
                }
            }
            Constants.REQUEST_CODE_ADD_POEM -> {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO: a new poem has been added successfully, work with a listener on the BaseActivity or BaseFragment to update the "active" pages
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}