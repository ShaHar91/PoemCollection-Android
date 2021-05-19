package com.shahar91.poems.ui.entry

import androidx.lifecycle.MutableLiveData
import be.appwise.core.util.SingleLiveEvent
import com.shahar91.poems.MyApp
import com.shahar91.poems.ui.base.PoemBaseViewModel

class EntryViewModel : PoemBaseViewModel() {

    val username = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordRepeat = MutableLiveData<String>()

    fun loginUser(email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        MyApp.authRepository.loginUser(email, password)
        resetValues()
        onSuccess()
    }

    fun registerUser(userName: String, email: String, password: String, onSuccess: () -> Unit) = launchAndLoad {
        MyApp.authRepository.registerUser(userName, email, password)
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