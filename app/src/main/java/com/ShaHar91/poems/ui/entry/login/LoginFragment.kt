package com.shahar91.poems.ui.entry.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.BaseActivity
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.entry.EntryListeners
import com.shahar91.poems.ui.entry.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.reuse_entry_social_footer.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseGoogleFragment<LoginViewModel, LoginComponent>() {
    lateinit var listeners: EntryListeners

    override fun createComponent(): LoginComponent {
        return DaggerLoginComponent.builder()
            .applicationComponent(appComponent())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        btnLogin.setOnClickListener { listeners.onLoginClicked() }
        btnLoginFacebook.setOnClickListener { listeners.onFacebookClicked() }
        btnLoginGoogle.setOnClickListener { listeners.onGoogleClicked() }

        tvLoginOrRegister.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance(true)
            registerFragment.listeners = this.listeners

            (requireActivity() as BaseActivity).replaceFragment(R.id.flEntryContainer, registerFragment, "NewTag", true)
        }

        (requireActivity() as EntryActivity).setHomeUpIcon(R.drawable.ic_close)
    }

    companion object {
        @JvmStatic
        fun newInstance(showBackIcon: Boolean) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(SHOW_BACK_ICON, showBackIcon)
                }
            }
    }
}