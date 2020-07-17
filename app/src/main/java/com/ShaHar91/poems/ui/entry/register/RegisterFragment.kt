package com.shahar91.poems.ui.entry.register

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
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.entry.EntryListeners
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.reuse_entry_social_footer.*

class RegisterFragment : BaseGoogleFragment<RegisterViewModel, RegisterComponent>() {
    lateinit var listeners: EntryListeners

    override fun createComponent(): RegisterComponent {
        return DaggerRegisterComponent.builder()
            .applicationComponent(appComponent())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        btnRegister.setOnClickListener { checkToRegister() }
        btnLoginFacebook.setOnClickListener { listeners.onFacebookClicked() }
        btnLoginGoogle.setOnClickListener { listeners.onGoogleClicked() }

        tvLoginOrRegister.setOnClickListener {
            requireActivity().onBackPressed()
        }

        (requireActivity() as EntryActivity).setHomeUpIcon(R.drawable.ic_navigation_back)
    }

    private fun checkToRegister() {
        // Reset all input fields
        tilEmail.setErrorLayout(null)
        tilUsername.setErrorLayout(null)
        tilPassword.setErrorLayout(null)
        tilReEnterPassword.setErrorLayout(null)

        var isValid = true
        val usernameText = tilUsername.editText?.text?.toString() ?: ""
        val emailText = tilEmail.editText?.text?.toString() ?: ""
        val passwordText = tilPassword.editText?.text?.toString() ?: ""
        val reEnterPasswordText = tilReEnterPassword.editText?.text?.toString() ?: ""

        if (usernameText.isBlank()) {
            tilUsername.setErrorLayout(getString(R.string.entry_invalid_username))
            isValid = false
        }

        if (viewModel.checkDataValidity(emailText, Patterns.EMAIL_ADDRESS)) {
            tilEmail.setErrorLayout(getString(R.string.entry_invalid_email))
            isValid = false
        }

        if (viewModel.checkDataValidity(passwordText, Constants.PASSWORD_PATTERN)) {
            tilPassword.setErrorLayout(getString(R.string.entry_invalid_password))
            isValid = false
        }

        if (passwordText != reEnterPasswordText) {
            tilReEnterPassword.setErrorLayout(getString(R.string.entry_invalid_re_enter_password))
            isValid = false
        }

        if (isValid) {
            viewModel.registerUser(usernameText, emailText, passwordText,
                {
                    listeners.onRegisterClicked()
                }, {
                    it.message?.let { message -> snackBar(message) }
                })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(showBackIcon: Boolean) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(SHOW_BACK_ICON, showBackIcon)
                }
            }
    }
}