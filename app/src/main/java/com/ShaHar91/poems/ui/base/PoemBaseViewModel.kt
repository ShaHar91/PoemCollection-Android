package com.shahar91.poems.ui.base

import be.appwise.core.ui.base.BaseViewModel
import java.util.regex.Pattern

/**
 * This is the ViewModel that will be used with every Activity
 */
abstract class PoemBaseViewModel : BaseViewModel() {
    fun checkDataValidity(inputText: String, pattern: Pattern): Boolean {
        return inputText.trim().isEmpty() || !pattern.matcher(inputText).matches()
    }
}