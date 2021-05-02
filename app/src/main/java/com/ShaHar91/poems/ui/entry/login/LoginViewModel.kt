package com.shahar91.poems.ui.entry.login

import com.shahar91.poems.MyApp
import com.shahar91.poems.ui.base.PoemBaseViewModel

class LoginViewModel : PoemBaseViewModel() {
    fun loginUser(email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        MyApp.authRepository.loginUser(email, password)
        onSuccess()
    }
}