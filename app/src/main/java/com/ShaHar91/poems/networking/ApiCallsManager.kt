package com.shahar91.poems.networking

import be.appwise.core.networking.Networking
import io.reactivex.disposables.Disposable
import retrofit2.http.Field
import retrofit2.http.Path

object ApiCallsManager {
    private val unprotected = Networking.getUnProtectedApiManager<NewApiManagerService>()
    private val protected = Networking.getProtectedApiManager<NewApiManagerService>()

    //<editor-fold desc="Poems">
    @JvmStatic
    fun getAllPoems(categoryId: String) = Networking.doCallRx(unprotected?.getPoems(categoryId)!!)

    suspend fun getPoemsForCategoryCr(categoryId: String) = Networking.doCallCr(unprotected?.getPoemsForCategoryId(categoryId)!!)

    @JvmStatic
    fun getPoemById(poemId: String, userId: String?) = Networking.doCallRx(unprotected?.getPoemById(poemId, userId)!!)

    @JvmStatic
    fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) = Networking.doCallRx(protected?.createPoem(poemTitle, poemBody, categoryList)!!)
    //</editor-fold>

    //<editor-fold desc="Categories">
    @JvmStatic
    fun getAllCategories() = Networking.doCallRx(unprotected?.getCategories()!!)

    suspend fun getAllCategoriesCr() = Networking.doCallCr(unprotected?.getCategoriesCr()!!)
    //</editor-fold>

    //<editor-fold desc="Auth">
    @JvmStatic
    fun loginUser(email: String, password: String) = Networking.doCallRx(unprotected?.loginUser(email, password)!!)

    @JvmStatic
    fun registerUser(userName: String, email: String, password: String) = Networking.doCallRx(protected?.registerUser(userName, email, password)!!)

    @JvmStatic
    fun getCurrentUser() = Networking.doCallRx(protected?.getCurrentUser()!!)
    //</editor-fold>

    //<editor-fold desc="Reviews">
    @JvmStatic
    fun getAllReviewsForPoem(poemId: String) = Networking.doCallRx(unprotected?.getReviewsByPoemId(poemId, 1)!!)

    @JvmStatic
    fun getOwnReviewForPoem(poemId: String, userId: String?) = Networking.doCallRx(unprotected?.getOwnReviewForPoem(poemId, userId)!!)

    @JvmStatic
    fun createReview(poemId: String, reviewText: String, reviewRating: Float) = Networking.doCallRx(protected?.createReview(poemId, reviewText, reviewRating)!!)

    @JvmStatic
    fun editReview(reviewId: String, reviewText: String, reviewRating: Float) = Networking.doCallRx(protected?.editReview(reviewId, reviewText, reviewRating)!!)

    @JvmStatic
    fun deleteReview(reviewId:String) = Networking.doCallRx(protected?.deleteReview(reviewId)!!)
    //</editor-fold>
}