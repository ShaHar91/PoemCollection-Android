package com.shahar91.poems.ui.home.profile

import be.appwise.core.ui.base.BaseViewModel
import be.appwise.networking.Networking
import com.shahar91.poems.domain.repository.IUserRepository

class ProfileViewModel(
    userRepository: IUserRepository
) : BaseViewModel() {

    val currentUser = userRepository.findCurrentUser()

    fun logout() {
        Networking.logout()
    }
}