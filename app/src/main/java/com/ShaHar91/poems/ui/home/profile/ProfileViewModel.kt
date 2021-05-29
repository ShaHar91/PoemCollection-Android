package com.shahar91.poems.ui.home.profile

import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import be.appwise.core.networking.Networking
import be.appwise.core.ui.base.BaseViewModel
import com.shahar91.poems.data.repositories.UserRepository

class ProfileViewModel(
    userRepository: UserRepository
) : BaseViewModel() {

    companion object {
        val factory = singleArgViewModelFactory(::ProfileViewModel)
    }

    val currentUser = userRepository.findCurrentUser()

    fun logout() {
        Networking.logout()
    }
}