package com.shahar91.poems.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import be.appwise.core.ui.base.BaseBindingVMFragment
import com.google.android.material.appbar.MaterialToolbar
import com.shahar91.poems.Constants
import com.shahar91.poems.NavGraphMainDirections
import com.shahar91.poems.R
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.utils.HawkManager

abstract class PoemBaseBindingVMFragment<B : ViewDataBinding> : BaseBindingVMFragment<B>() {
    protected abstract fun getToolbar(): MaterialToolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(getToolbar())
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        getToolbar().setupWithNavController(navController, appBarConfiguration)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                if (HawkManager.currentUserId != null) {
                    NavGraphMainDirections.actionGlobalProfileFragment().run(findNavController()::navigate)
                } else {
                    // start the EntryActivity to make sure the user gets logged in
                    startActivityForResult(
                        EntryActivity.newIntent(requireContext()),
                        Constants.REQUEST_CODE_PROFILE
                    )
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        requireActivity().invalidateOptionsMenu()
    }

    fun themeSwipeToRefresh(srl: SwipeRefreshLayout) {
        srl.run {
            setColorSchemeResources(R.color.colorWhite)
            setProgressBackgroundColorSchemeResource(R.color.colorPrimary)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.REQUEST_CODE_PROFILE && resultCode == Activity.RESULT_OK) {
            // A user has been logged in successfully
            NavGraphMainDirections.actionGlobalProfileFragment().run(findNavController()::navigate)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}