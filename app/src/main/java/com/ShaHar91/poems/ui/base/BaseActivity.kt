package com.shahar91.poems.ui.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.shahar91.poems.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Default BaseActivity that will be used for most of the Activities.
 *
 * @param <VM> The ViewModel working with the Activity
</VM> */
abstract class BaseActivity<VM : BaseGoogleViewModel> : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected lateinit var viewModel: VM

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

    protected fun configureToolbar(toolbar: Toolbar, showBackIcon: Boolean,
        @StringRes toolbarTitleRes: Int, @DrawableRes drawableRes: Int = R.drawable.ic_navigation_back) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setTitle(toolbarTitleRes)
            if (showBackIcon) {
                it.setDisplayHomeAsUpEnabled(true)
                it.setHomeAsUpIndicator(drawableRes)
                toolbar.setNavigationOnClickListener { onToolbarNavigationIconClicked() }
            }
        }
    }

    protected fun onToolbarNavigationIconClicked() {
        onBackPressed()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun addDisposable(disposable: Disposable?) {
        compositeDisposable.add(disposable!!)
    }

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