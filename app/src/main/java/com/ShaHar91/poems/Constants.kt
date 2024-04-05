package com.shahar91.poems

import java.util.regex.Pattern

object Constants {
    const val REQUEST_CODE_NEW_USER = 100
    const val REQUEST_CODE_ADD_POEM = 101
    const val REQUEST_CODE_PROFILE = 102
    val PASSWORD_PATTERN : Pattern = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{6,24}")

    const val ACTIVITY_RESPONSE_RATING_KEY = "rating"
    const val NETWORK_BEARER = "Bearer"
}