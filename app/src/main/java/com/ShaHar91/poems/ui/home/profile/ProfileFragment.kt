package com.shahar91.poems.ui.home.profile

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.viewModels
import be.appwise.core.extensions.fragment.snackBar
import com.shahar91.poems.MyApp
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentProfileBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment

class ProfileFragment : PoemBaseBindingVMFragment<FragmentProfileBinding>() {

    override fun getLayout() = R.layout.fragment_profile
    override val mViewModel: ProfileViewModel by viewModels { getViewModelFactory() }
    override fun getToolbar() = mBinding.tbProfile
    override fun getViewModelFactory() = ProfileViewModel.factory(MyApp.userRepository)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.run {
            viewModel = mViewModel
        }

        initViews()
    }

    private fun initViews() {
        mBinding.run {
            ivEditImage.setOnClickListener {
                snackBar("edit image!")
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        menu.findItem(R.id.action_profile).isVisible = false
    }
}