package com.shahar91.poems.ui.base

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.shahar91.poems.MyApp
import com.shahar91.poems.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment<VM : BaseGoogleViewModel> :
    Fragment() {

    protected lateinit var viewModel: VM
    private lateinit var parentActivity: AppCompatActivity
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = requireActivity() as AppCompatActivity
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun configureToolbar(toolbar: Toolbar, color: Int? = null,
        showTitle: Boolean = true) {
        parentActivity.setSupportActionBar(toolbar)
        val showBackIcon = arguments != null && arguments!!.getBoolean(
            SHOW_BACK_ICON)
        if (showBackIcon) {
            parentActivity.supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(true)
                it.setHomeAsUpIndicator(R.drawable.ic_navigation_back)
            }
            color?.let { toolbar.navigationIcon?.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN) }
            if (!showTitle) {
                toolbar.title = null
            }
            toolbar.setNavigationOnClickListener { parentActivity.onBackPressed() }
        }
    }

    fun tintMenuIcons(menu: Menu, color: Int) {
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            val normalDrawable = item.icon
            if (normalDrawable != null) {
                val wrapDrawable = DrawableCompat.wrap(
                    normalDrawable)
                DrawableCompat.setTint(wrapDrawable, color)
                item.icon = wrapDrawable
                //            } else if (item.getActionView() != null && item.getActionView() instanceof MediaRouteButton) {
                //                // Google Cast Button
                //                CastUtils.setCastButtonColor(getContext(), (MediaRouteButton) item.getActionView(), color);
            }
        }
    }

    protected fun addDisposable(disposable: Disposable?) {
        compositeDisposable.add(disposable!!)
    }

    protected fun <T> setListener(): T {
        val listener: T? = if (parentFragment != null) {
            parentFragment as T?
        } else {
            try {
                activity as T?
            } catch (e: ClassCastException) {
                null
            }
        }
        if (listener != null) {
            return listener
        }
        throw IllegalStateException("parent must implement listener")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in childFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val SHOW_BACK_ICON = "SHOW_BACK_ICON"
    }
}