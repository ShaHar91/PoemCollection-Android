package com.shahar91.poems.ui.entry.register

import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class RegisterViewModel : PoemBaseViewModel() {
    fun registerUser(userName: String, email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        AuthRepository.registerUser(userName, email, password)
        onSuccess()
    }
}