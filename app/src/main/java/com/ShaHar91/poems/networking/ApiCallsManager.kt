package com.shahar91.poems.networking

import be.appwise.core.networking.Networking
import io.reactivex.disposables.Disposable

object ApiCallsManager {
    private val unprotected = Networking.getUnProtectedApiManager<NewApiManagerService>()
    private val protected = Networking.getProtectedApiManager<NewApiManagerService>()

    @JvmStatic
    fun getAllPoems(categoryId: String) = Networking.doCallRx(unprotected?.getPoems(categoryId)!!)

    @JvmStatic
    fun getPoemById(poemId: String, userId: String?) = Networking.doCallRx(unprotected?.getPoemById(poemId, userId)!!)

    @JvmStatic
    fun getAllCategories() = Networking.doCallRx(unprotected?.getCategories()!!)

    @JvmStatic
    fun getAllReviewsForPoem(poemId: String) = Networking.doCallRx(unprotected?.getReviewsByPoemId(poemId, 1)!!)

    @JvmStatic
    fun getOwnReviewForPoem(poemId: String, userId: String?) = Networking.doCallRx(unprotected?.getOwnReviewForPoem(poemId, userId)!!)

    @JvmStatic
    fun loginUser(email: String, password: String) = Networking.doCallRx(unprotected?.loginUser(email, password)!!)

    @JvmStatic
    fun getCurrentUser() = Networking.doCallRx(protected?.getCurrentUser()!!)
}