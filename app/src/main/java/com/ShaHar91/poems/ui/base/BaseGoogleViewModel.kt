package com.shahar91.poems.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.regex.Pattern

/**
 * This is the ViewModel that will be used with every Activity
 */
abstract class BaseGoogleViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun checkDataValidity(inputText: String, pattern: Pattern): Boolean {
        return inputText.trim().isEmpty() || !pattern.matcher(inputText).matches()
    }
}