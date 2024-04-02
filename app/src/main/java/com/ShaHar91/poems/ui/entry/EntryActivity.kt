package com.shahar91.poems.ui.entry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
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
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    override val mViewModel: EntryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_entry)
        mBinding.lifecycleOwner = this

        setSupportActionBar(mBinding.tbEntry)

        val host = supportFragmentManager.findFragmentById(R.id.entry_nav_host_fragment) as NavHostFragment
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, _, _ ->
            mBinding.tbEntry.run {
                navigationIcon = ContextCompat.getDrawable(this@EntryActivity, R.drawable.ic_close)
                setNavigationIconTint(ContextCompat.getColor(this@EntryActivity, R.color.colorWhite))
                setNavigationOnClickListener {
                    setResult(Activity.RESULT_CANCELED, intent)
                    finish()
                }
            }
        }

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
}