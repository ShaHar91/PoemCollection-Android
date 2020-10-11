package com.shahar91.poems.ui.base

import android.os.Bundle
import be.appwise.core.ui.base.BaseFragment

abstract class PoemBaseFragment<VM : PoemBaseViewModel> : BaseFragment<VM>(){
    protected fun <T> setListener(): T {
        val listener: T? = if (parentFragment != null) {
            parentFragment as T?
        } else {
            try {
                activity as T?
            } catch (e: ClassCastException) {
                null
            }
        }
        if (listener != null) {
            return listener
        }
        throw IllegalStateException("parent must implement listener")
    }

}