package com.shahar91.poems.ui.entry.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import be.appwise.core.ui.base.BaseBindingVMFragment
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentLoginBinding
import com.shahar91.poems.ui.entry.EntryViewModel
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.TextInputLayoutValidator
import com.thedeadpixelsociety.passport.passport
import com.thedeadpixelsociety.passport.required

class LoginFragment : BaseBindingVMFragment<FragmentLoginBinding>() {

    override val mViewModel: EntryViewModel by activityViewModels()
    override fun getLayout() = R.layout.fragment_login

    private lateinit var validation: Passport

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
                //TODO: when error shows the snackBar is behind the keyboard because the layout is not resizing when keyboard opens up
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment().run(findNavController()::navigate)
            }
        }
    }

    private fun checkToLogin() {
        if (validation.validate(requireActivity())) {
            mViewModel.loginUser(mViewModel.email.value!!, mViewModel.password.value!!) {
                requireActivity().run {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}