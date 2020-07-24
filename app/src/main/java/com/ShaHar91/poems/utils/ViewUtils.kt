package com.shahar91.poems.utils

import android.content.Context
import com.shahar91.poems.R

object ViewUtils {
    @JvmStatic
    fun isTablet(context: Context): Boolean {
        return context.resources.getBoolean(R.bool.isTablet)
    }
}