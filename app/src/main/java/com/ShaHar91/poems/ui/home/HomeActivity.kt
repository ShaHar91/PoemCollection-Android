package com.shahar91.poems.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import be.appwise.core.ui.base.BaseActivity
import com.shahar91.poems.Constants
import com.shahar91.poems.NavGraphMainDirections
import com.shahar91.poems.R
import com.shahar91.poems.databinding.ActivityHomeBinding
import com.shahar91.poems.ui.add.AddPoemActivity.Companion.startWithIntent
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.utils.HawkUtils

class HomeActivity : BaseActivity() {
    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var host: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        // replace the startup 'Splash Theme' with the default AppTheme
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mBinding.lifecycleOwner = this

        initViews()
    }

    private fun initViews() {
        mBinding.fabAddPoem.setOnClickListener {
            if (!HawkUtils.hawkCurrentUserId.isNullOrBlank()) {
                startAddPoem()
            } else {
                // start the EntryActivity to make sure the user gets logged in
                startActivityForResult(EntryActivity.newIntent(this),
                    Constants.REQUEST_CODE_NEW_USER)
            }
        }

        host = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        host.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.profileFragment -> mBinding.fabAddPoem.hide()
                else -> mBinding.fabAddPoem.show()
            }
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
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}