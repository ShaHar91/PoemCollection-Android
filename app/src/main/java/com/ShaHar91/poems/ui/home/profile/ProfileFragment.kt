package com.shahar91.poems.ui.home.profile

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import be.appwise.core.networking.Networking
import com.shahar91.poems.R
import com.shahar91.poems.databinding.FragmentProfileBinding
import com.shahar91.poems.ui.base.PoemBaseBindingVMFragment
import com.shahar91.poems.utils.HawkUtils

class ProfileFragment : PoemBaseBindingVMFragment<FragmentProfileBinding>() {

    override fun getLayout() = R.layout.fragment_profile
    override val mViewModel: ProfileViewModel by viewModels()
    override fun getToolbar() = mBinding.mergeToolbar.toolbar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.run {
            viewModel = mViewModel
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        menu.findItem(R.id.action_profile).isVisible = false
    }
}