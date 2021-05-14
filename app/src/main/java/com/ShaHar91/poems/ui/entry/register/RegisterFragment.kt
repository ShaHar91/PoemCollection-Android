package com.shahar91.poems.ui.entry.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import be.appwise.core.extensions.view.setErrorLayout
import be.appwise.core.ui.base.BaseFragment.Companion.SHOW_BACK_ICON
import be.appwise.core.ui.base.BaseVMFragment
import com.shahar91.poems.Constants
import com.shahar91.poems.R
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.entry.EntryListeners
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.reuse_entry_social_footer.*

class RegisterFragment : BaseVMFragment() {
    companion object {
        @JvmStatic
        fun newInstance(showBackIcon: Boolean) =
                RegisterFragment().apply {
                    arguments = Bundle().apply {
                        putBoolean(SHOW_BACK_ICON, showBackIcon)
                    }
                }
    }

    lateinit var listeners: EntryListeners

    override val mViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

        if (mViewModel.checkDataValidity(emailText, Patterns.EMAIL_ADDRESS)) {
            tilEmail.setErrorLayout(getString(R.string.entry_invalid_email))
            isValid = false
        }

        if (mViewModel.checkDataValidity(passwordText, Constants.PASSWORD_PATTERN)) {
            tilPassword.setErrorLayout(getString(R.string.entry_invalid_password))
            isValid = false
        }

        if (passwordText != reEnterPasswordText) {
            tilReEnterPassword.setErrorLayout(getString(R.string.entry_invalid_re_enter_password))
            isValid = false
        }

        if (isValid) {
            mViewModel.registerUser(usernameText, emailText, passwordText) {
                listeners.onRegisterSuccessful()
            }
        }
    }
}