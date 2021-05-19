package com.shahar91.poems.ui.home.profile

import be.appwise.core.networking.Networking
import be.appwise.core.ui.base.BaseViewModel

class ProfileViewModel : BaseViewModel() {
    fun logout() {
        Networking.logout()
    }
}