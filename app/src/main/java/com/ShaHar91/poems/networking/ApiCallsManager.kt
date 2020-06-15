package com.shahar91.poems.networking

import be.appwise.core.networking.Networking

object ApiCallsManager {
    private val unprotected = Networking.getUnProtectedApiManager<NewApiManagerService>()
    private val protected = Networking.getProtectedApiManager<NewApiManagerService>()

    @JvmStatic
    fun getAllPoems() = Networking.doCallRx(unprotected?.getPoems()!!)

    @JvmStatic
    fun getAllCategories() = Networking.doCallRx(unprotected?.getCategories()!!)
}