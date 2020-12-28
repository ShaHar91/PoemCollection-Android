package com.shahar91.poems.ui.entry.login

import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class LoginViewModel : PoemBaseViewModel() {
    fun loginUser(email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        AuthRepository.loginUserCr(email, password)
        onSuccess()
    }
}