package com.shahar91.poems.ui.entry.register

import android.app.Activity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import be.appwise.core.extensions.fragment.hideKeyboard
import be.appwise.core.extensions.fragment.snackBar
import be.appwise.core.ui.base.BaseBindingVMFragment
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentRegisterBinding
import com.shahar91.poems.ui.entry.EntryViewModel
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.TextInputLayoutValidator
import com.thedeadpixelsociety.passport.passport
import com.thedeadpixelsociety.passport.required
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class RegisterFragment : BaseBindingVMFragment<FragmentRegisterBinding>() {

    override val mViewModel: EntryViewModel by activityViewModel()
    override fun getLayout() = R.layout.fragment_register

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

            rules<String>(mBinding.tilUsername) {
                required(getString(R.string.entry_invalid_username))
            }

            rules<String>(mBinding.tilEmail) {
                required(getString(R.string.entry_invalid_email))
            }

            rules<String>(mBinding.tilPassword) {
                required(getString(R.string.entry_invalid_password))
            }

            rules<String>(mBinding.tilReEnterPassword) {
                required(getString(R.string.entry_invalid_re_enter_password))
            }
        }
    }

    private fun initViews() {
        mBinding.run {
            btnRegister.setOnClickListener { checkToRegister() }
            socialFooter.btnLoginFacebook.setOnClickListener { mViewModel.facebookLoginClicked.postValue(true) }
            socialFooter.btnLoginGoogle.setOnClickListener { mViewModel.googleLoginClicked.postValue(true) }

            tvLoginOrRegister.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun checkToRegister() {
        if (validation.validate(requireActivity())) {
            hideKeyboard()

            mViewModel.registerUser(
                mViewModel.username.value!!,
                mViewModel.email.value!!,
                mViewModel.password.value!!
            ) {
                requireActivity().run {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        snackBar(throwable.message ?: "Something went wrong.")
    }
}