package com.shahar91.poems

import java.util.regex.Pattern

object Constants {
    const val CURRENT_USER_ID = 0
    const val REQUEST_CODE_NEW_USER = 100
    const val REQUEST_CODE_ADD_POEM = 101
    const val DEFAULT_REFRESH_LAYOUT_DURATION: Long = 1500 // 1.5 seconds
    val PASSWORD_PATTERN : Pattern = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{6,24}")
}