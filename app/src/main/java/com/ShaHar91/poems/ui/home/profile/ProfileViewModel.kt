package com.shahar91.poems.ui.home.profile

import be.appwise.core.ui.base.BaseViewModel
import be.appwise.networking.Networking
import com.shahar91.poems.data.repositories.IUserRepository
import com.shahar91.poems.data.repositories.UserRepository

class ProfileViewModel(
    userRepository: IUserRepository
) : BaseViewModel() {

    val currentUser = userRepository.findCurrentUser()

    fun logout() {
        Networking.logout()
    }
}