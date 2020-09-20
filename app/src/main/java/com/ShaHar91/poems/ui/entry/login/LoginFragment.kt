package com.shahar91.poems.ui.entry.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import be.appwise.core.extensions.fragment.snackBar
import be.appwise.core.extensions.view.setErrorLayout
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.BaseGoogleFragment
import com.shahar91.poems.ui.base.BaseGoogleMobileActivity
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.entry.EntryListeners
import com.shahar91.poems.ui.entry.register.RegisterFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.reuse_entry_social_footer.*

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

        btnLogin.setOnClickListener { checkToLogin() }
        btnLoginFacebook.setOnClickListener { this.listeners.onFacebookClicked() }
        btnLoginGoogle.setOnClickListener { this.listeners.onGoogleClicked() }

        tvLoginOrRegister.setOnClickListener {
            //TODO: when error shows the snackbar is behind the keyboard because the layout is not resizing when keyboard opens up
            val registerFragment = RegisterFragment.newInstance(true)
            registerFragment.listeners = this.listeners

            (requireActivity() as BaseGoogleMobileActivity<*, *>).replaceFragment(R.id.flEntryContainer,
                registerFragment, "NewTag", true)
        }

        (requireActivity() as EntryActivity).setHomeUpIcon(R.drawable.ic_close)
    }

    private fun checkToLogin() {
        // Reset both input fields
        tilEmail.setErrorLayout(null)
        tilPassword.setErrorLayout(null)

        var isValid = true
        val emailText = tilEmail.editText?.text?.toString() ?: ""
        val passwordText = tilPassword.editText?.text?.toString() ?: ""

        if (viewModel.checkDataValidity(emailText, Patterns.EMAIL_ADDRESS)) {
            // Email is not valid, show error on emailEditText
            tilEmail.setErrorLayout(getString(R.string.entry_invalid_email))
            isValid = false
        }

        if (viewModel.checkDataValidity(passwordText, Constants.PASSWORD_PATTERN)) {
            // Password is not valid, show error on passwordEditText
            tilPassword.setErrorLayout(getString(R.string.entry_invalid_password))
            isValid = false
        }

        if (isValid) {
            viewModel.loginUser(emailText, passwordText,
                {
                    listeners.onLoginSuccessful()
                }, {
                    it.message?.let { message -> snackBar(message) }
                })
        }
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