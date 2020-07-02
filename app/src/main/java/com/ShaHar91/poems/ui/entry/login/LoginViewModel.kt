package com.shahar91.poems.ui.entry.login

import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.redux.AppState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.yheriatovych.reductor.Store
import javax.inject.Inject

class LoginViewModel @Inject internal constructor(store: Store<AppState>) : BaseGoogleViewModel(store) {
    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit) =
        AuthRepository.loginUser(email, password, onSuccess, onError)
}