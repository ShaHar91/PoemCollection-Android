package com.shahar91.poems.ui.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import be.appwise.core.ui.base.BaseBindingVMActivity
import be.appwise.core.ui.base.BaseVMActivity
import com.shahar91.poems.R

/**
 * Default BaseActivity that will be used for most of the Activities.
 *
 * @param <VM> The ViewModel working with the Activity
</VM> */
abstract class PoemBaseActivity<B : ViewDataBinding> : BaseBindingVMActivity<B>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    protected val isTablet: Boolean
        get() =  resources.getBoolean(R.bool.isTablet)

    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment?,
        addToBackstack: Boolean) {
        replaceFragment(containerViewId, fragment, null, addToBackstack)
    }

    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment?, tag: String?,
        addToBackstack: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment!!, tag)
        if (addToBackstack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    protected fun finishThisActivity(resultOk: Int, intent: Intent? = null) {
        setResult(resultOk, intent)
        finish()
    }
}