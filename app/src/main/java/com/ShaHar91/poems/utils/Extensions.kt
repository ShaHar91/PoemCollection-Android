package com.shahar91.poems.utils

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.networking.UnProtectedRestClient

fun BaseRepository.unprotectedClient(): NewApiManagerService {
    return UnProtectedRestClient.getService
}

fun BaseRepository.protectedClient(): NewApiManagerService {
    return ProtectedRestClient.getService
}