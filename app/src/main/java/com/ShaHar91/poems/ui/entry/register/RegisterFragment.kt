package com.shahar91.poems.ui.entry.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
        setError(tilEmail, null)
        setError(tilUsername, null)
        setError(tilPassword, null)
        setError(tilReEnterPassword, null)

        var isValid = true
        val usernameText = tilUsername.editText?.text ?: ""
        val emailText = tilEmail.editText?.text ?: ""
        val passwordText = tilPassword.editText?.text ?: ""
        val reEnterPasswordText = tilReEnterPassword.editText?.text ?: ""

        if (usernameText.isBlank()) {
            setError(tilUsername, "Please fill in a valid username")
            isValid = false
        }

        if (viewModel.checkDataValidity(emailText.toString(), Patterns.EMAIL_ADDRESS)) {
            setError(tilEmail, "Please fill in a valid email")
            isValid = false
        }

        if (viewModel.checkDataValidity(passwordText.toString(), Constants.PASSWORD_PATTERN)) {
            setError(tilPassword, "Please fill in a password with at least 6 characters")
            isValid = false
        }

        if (passwordText != reEnterPasswordText) {
            setError(tilReEnterPassword, "Please make sure both passwords are the same")
            isValid = false
        }

        if (isValid) {
            //TODO: do the actual register call to the backend, if successful registered call this listener!!
            listeners.onRegisterClicked()
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