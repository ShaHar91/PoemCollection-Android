package com.shahar91.poems.ui.entry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.logging.logd
import com.google.android.material.appbar.AppBarLayout
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity
import com.shahar91.poems.ui.entry.login.LoginFragment
import kotlinx.android.synthetic.main.activity_entry.*
import timber.log.Timber

class EntryActivity : BaseGoogleMobileActivity<EntryViewModel, EntryComponent>() {
    private var loginFragment: LoginFragment = LoginFragment.newInstance(false)

    private var entryListeners = object : EntryListeners {
        override fun onLoginSuccessful() {
            Timber.tag("entryListeners").d("onLoginClicked")
            finishThisActivity(Activity.RESULT_OK, intent)
        }

        override fun onRegisterSuccessful() {
            Timber.tag("entryListeners").d("onRegisterClicked")
            finishThisActivity(Activity.RESULT_OK, intent)
        }

        override fun onGoogleClicked() {
            Timber.tag("entryListeners").d("onGoogleClicked")
            finishThisActivity(Activity.RESULT_CANCELED, intent)
        }

        override fun onFacebookClicked() {
            Timber.tag("entryListeners").d("onFacebookClicked")
            finishThisActivity(Activity.RESULT_CANCELED, intent)
        }
    }

    companion object {
        private const val TAG_LOGIN = "TagLogin"

        @JvmStatic
        @JvmOverloads
        fun startWithIntent(context: Context, rating: Float? = null): Intent {
            val intent = Intent(context, EntryActivity::class.java)
            if (rating != null) {
                intent.putExtra("rating", rating)
            }
            return intent
        }
    }

    override fun createComponent(): EntryComponent {
        return DaggerEntryComponent.builder()
            .applicationComponent(appComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        initToolbar()

        showLoginFragment(savedInstanceState)

        //TODO: when softKeyboard opens, resize full screen to not be behind the keyboard!!!
    }

    private fun initToolbar() {
        setSupportActionBar(tbEntry)
        setAppBarLayoutListener()
        // This navigationListener should be after the actionBar has been set
        tbEntry.setNavigationOnClickListener { onBackPressed() }
    }

    /**
     * The toolbar title should only be filled when the toolbar is collapsed.
     */
    private fun setAppBarLayoutListener() {
        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                collapsing_toolbar.title = resources.getString(R.string.app_name)
                isShow = true
            } else if (isShow) {
                collapsing_toolbar.title = " " //careful, there should a space between double quote otherwise this hack wont work
                isShow = false
            }
        })
    }

    private fun showLoginFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            replaceFragment(R.id.flEntryContainer, loginFragment, TAG_LOGIN, false)
        } else {
            logd("FragmentByTag", "Find Login Fragment By Tag")
            loginFragment = supportFragmentManager.findFragmentByTag(TAG_LOGIN) as LoginFragment
        }
        loginFragment.listeners = entryListeners
    }

    /**
     * Set a custom icon for Up-navigation depending on login or register fragment being active
     *
     * @param icNavigationBack icon identifier to be set in the toolbar
     */
    fun setHomeUpIcon(icNavigationBack: Int) {
        supportActionBar?.setHomeAsUpIndicator(
            ContextCompat.getDrawable(this, icNavigationBack)?.apply {
                setTint(ContextCompat.getColor(this@EntryActivity, R.color.colorWhite))
            }
        )
    }
}