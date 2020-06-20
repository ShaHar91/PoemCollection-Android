package com.shahar91.poems.utils

import be.appwise.core.util.HawkValueDelegate

object HawkUtils {
    private const val HAWK_CURRENT_USER_ID = "CurrentUserId"

    @JvmStatic
    var hawkCurrentUserId: String by HawkValueDelegate(HAWK_CURRENT_USER_ID, "")
}