package com.shahar91.poems.ui.entry.register

import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import javax.inject.Inject

class RegisterViewModel @Inject internal constructor() : BaseGoogleViewModel() {
    fun registerUser(userName: String, email: String, password: String, onSuccess: () -> Unit, onError:(Throwable) -> Unit) =
        AuthRepository.registerUser(userName, email, password, onSuccess, onError)
}