package com.shahar91.poems.ui.entry.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.fragment.hideKeyboard
import be.appwise.core.extensions.fragment.showSnackBar
import be.appwise.core.extensions.fragment.snackBar
import be.appwise.core.ui.base.BaseBindingVMFragment
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentLoginBinding
import com.shahar91.poems.ui.entry.EntryViewModel
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.TextInputLayoutValidator
import com.thedeadpixelsociety.passport.passport
import com.thedeadpixelsociety.passport.required
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class LoginFragment : BaseBindingVMFragment<FragmentLoginBinding>() {

    override val mViewModel: EntryViewModel by activityViewModel()
    override fun getLayout() = R.layout.fragment_login

    private lateinit var validation: Passport

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.run {
            viewModel = mViewModel.apply {
                resetValues()
            }
        }

        initValidation()
        initViews()
    }

    private fun initValidation() {
        validation = passport {
            Passport.validatorFactory { TextInputLayoutValidator() }

            rules<String>(mBinding.tilEmail) {
                required(getString(R.string.entry_invalid_email))
            }

            rules<String>(mBinding.tilPassword) {
                required(getString(R.string.entry_invalid_password))
            }
        }
    }

    private fun initViews() {
        mBinding.run {
            btnLogin.setOnClickListener { checkToLogin() }
            socialFooter.btnLoginFacebook.setOnClickListener { mViewModel.facebookLoginClicked.postValue(true) }
            socialFooter.btnLoginGoogle.setOnClickListener { mViewModel.googleLoginClicked.postValue(true) }

            tvLoginOrRegister.setOnClickListener {
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment().run(findNavController()::navigate)
            }
        }
    }

    private fun checkToLogin() {
        if (validation.validate(requireActivity())) {
            hideKeyboard()

            mViewModel.loginUser(mViewModel.email.value!!, mViewModel.password.value!!) {
                requireActivity().run {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        showSnackBar(throwable.message ?: "Something went wrong.")
    }
}