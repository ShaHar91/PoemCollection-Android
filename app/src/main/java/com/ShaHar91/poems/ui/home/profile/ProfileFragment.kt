package com.shahar91.poems.ui.home.profile

import android.os.Bundle
import android.view.Menu
import android.view.View
import be.appwise.core.extensions.fragment.showSnackBar
import be.appwise.core.extensions.fragment.snackBar
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentProfileBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : PoemBaseBindingVMFragment<FragmentProfileBinding>() {

    override fun getLayout() = R.layout.fragment_profile
    override val mViewModel: ProfileViewModel by viewModel()
    override fun getToolbar() = mBinding.tbProfile
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.run {
            viewModel = mViewModel
        }

        initViews()
    }

    private fun initViews() {
        mBinding.run {
            ivEditImage.setOnClickListener {
                showSnackBar("edit image!")
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        menu.findItem(R.id.action_profile).isVisible = false
    }
}