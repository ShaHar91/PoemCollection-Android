package com.shahar91.poems.ui.entry

import androidx.lifecycle.MutableLiveData
import be.appwise.core.util.SingleLiveEvent
import com.shahar91.poems.MyApp
import com.shahar91.poems.data.repositories.IAuthRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

// Using a "sharedViewModel", followed this 'tutorial'
// https://developer.android.google.cn/codelabs/basic-android-kotlin-training-shared-viewmodel#0
class EntryViewModel(
    private val authRepository: IAuthRepository
) : PoemBaseViewModel() {

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordRepeat = MutableLiveData<String>()

    fun loginUser(email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        authRepository.loginUser(email, password)
        resetValues()
        onSuccess()
    }

    fun registerUser(userName: String, email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        authRepository.registerUser(userName, email, password)
        resetValues()
        onSuccess()
    }

    val facebookLoginClicked = SingleLiveEvent<Boolean>()
    val googleLoginClicked = SingleLiveEvent<Boolean>()

    fun resetValues() {
        username.postValue("")
        email.postValue("")
        password.postValue("")
        passwordRepeat.postValue("")
    }
}