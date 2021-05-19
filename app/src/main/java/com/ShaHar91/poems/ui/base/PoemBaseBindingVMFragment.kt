package com.shahar91.poems.ui.base

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
import be.appwise.core.ui.base.BaseBindingVMFragment
import com.google.android.material.appbar.MaterialToolbar
import com.shahar91.poems.NavGraphMainDirections
import com.shahar91.poems.R
import com.shahar91.poems.utils.HawkUtils

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

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_profile).isVisible = (HawkUtils.hawkCurrentUserId != null)

        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_profile -> {
                NavGraphMainDirections.actionGlobalProfileFragment().run(findNavController()::navigate)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        requireActivity().invalidateOptionsMenu()
    }
}