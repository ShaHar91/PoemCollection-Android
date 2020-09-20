package com.shahar91.poems.ui.entry.login

import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.ui.base.BaseGoogleViewModel
import javax.inject.Inject

class LoginViewModel @Inject internal constructor() : BaseGoogleViewModel() {
    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit) =
        AuthRepository.loginUser(email, password, onSuccess, onError)
}