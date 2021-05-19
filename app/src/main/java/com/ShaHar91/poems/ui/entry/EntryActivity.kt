package com.shahar91.poems.ui.entry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import be.appwise.core.ui.base.BaseVMActivity
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.databinding.ActivityEntryBinding

class EntryActivity : BaseVMActivity() {
    companion object {

        fun newIntent(context: Context, rating: Float? = null): Intent {
            val intent = Intent(context, EntryActivity::class.java)
            if (rating != null) {
                intent.putExtra(Constants.ACTIVITY_RESPONSE_RATING_KEY, rating)
            }
            return intent
        }
    }

    private lateinit var mBinding: ActivityEntryBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override val mViewModel: EntryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
        mBinding.lifecycleOwner = this

        setSupportActionBar(mBinding.tbEntry)

        val host = supportFragmentManager.findFragmentById(R.id.entry_nav_host_fragment) as NavHostFragment
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        initObservers()
    }

    private fun initObservers() {
        mViewModel.facebookLoginClicked.observe(this) {
            Log.d("SomethingTag", "Facebook Login clicked: $it")
        }

        mViewModel.googleLoginClicked.observe(this) {
            Log.d("SomethingTag", "Google Login clicked: $it")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.entry_nav_host_fragment).navigateUp(appBarConfiguration)
    }

    //    /**
    //     * The toolbar title should only be filled when the toolbar is collapsed.
    //     */
    //    private fun setAppBarLayoutListener() {
    //        var isShow = true
    //        var scrollRange = -1
    //        mBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
    //            if (scrollRange == -1) {
    //                scrollRange = barLayout?.totalScrollRange!!
    //            }
    //            if (scrollRange + verticalOffset == 0) {
    //                mBinding.collapsingToolbar.title = resources.getString(R.string.app_name)
    //                isShow = true
    //            } else if (isShow) {
    //                mBinding.collapsingToolbar.title = " " //careful, there should a space between double quote otherwise this hack wont work
    //                isShow = false
    //            }
    //        })
    //    }
    //
    //    /**
    //     * Set a custom icon for Up-navigation depending on login or register fragment being active
    //     *
    //     * @param icNavigationBack icon identifier to be set in the toolbar
    //     */
    //    fun setHomeUpIcon(icNavigationBack: Int) {
    //        supportActionBar?.setHomeAsUpIndicator(
    //                ContextCompat.getDrawable(this, icNavigationBack)?.apply {
    //                    setTint(ContextCompat.getColor(this@EntryActivity, R.color.colorWhite))
    //                }
    //        )
    //    }
}