package com.shahar91.poems.utils

import be.appwise.core.util.HawkValueDelegate

object HawkManager: IHawkManager {
    private const val CURRENT_USER_ID = "CurrentUserId"

    override var currentUserId: String? by HawkValueDelegate(CURRENT_USER_ID, null)
}

interface IHawkManager {
    var currentUserId: String?
}