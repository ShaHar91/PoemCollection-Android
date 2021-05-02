package com.shahar91.poems.ui.entry.register

import com.shahar91.poems.MyApp
import com.shahar91.poems.ui.base.PoemBaseViewModel

class RegisterViewModel : PoemBaseViewModel() {
    fun registerUser(userName: String, email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        MyApp.authRepository.registerUser(userName, email, password)
        onSuccess()
    }
}