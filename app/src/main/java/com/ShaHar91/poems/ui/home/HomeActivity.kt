package com.shahar91.poems.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.ui.add.AddPoemActivity.Companion.startWithIntent
import com.shahar91.poems.ui.base.PoemBaseActivity
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.utils.HawkUtils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : PoemBaseActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override val mViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // replace the startup 'Splash Theme' with the default AppTheme
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val host = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.categoryFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)

        fabAddPoem.setOnClickListener {
            if (!HawkUtils.hawkCurrentUserId.isNullOrBlank()) {
                startAddPoem()
            } else {
                // start the EntryActivity to make sure the user gets logged in
                startActivityForResult(EntryActivity.startWithIntent(this),
                        Constants.REQUEST_CODE_NEW_USER)
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
            Constants.REQUEST_CODE_ADD_POEM -> {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO: a new poem has been added successfully, work with a listener on the BaseActivity or BaseFragment to update the "active" pages
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp(appBarConfiguration)
    }
}