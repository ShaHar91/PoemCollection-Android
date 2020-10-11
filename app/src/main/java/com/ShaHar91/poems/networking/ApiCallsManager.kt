package com.shahar91.poems.networking

import be.appwise.core.networking.Networking
import be.appwise.core.networking.models.AccessToken
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shahar91.poems.data.models.Poem
import io.reactivex.Observable

object ApiCallsManager {
    private val unprotected = Networking.getUnProtectedApiManager<NewApiManagerService>()
    private val protected = Networking.getProtectedApiManager<NewApiManagerService>()

    //<editor-fold desc="Poems">
    @JvmStatic
    fun getAllPoems(categoryId: String): Observable<NetworkResponse<JsonArray>> = Observable.empty()

    suspend fun getPoemsForCategoryCr(categoryId: String) =
        Networking.doCall(unprotected?.getPoemsForCategoryId(categoryId)!!)

    @JvmStatic
    fun getPoemById(poemId: String, userId: String?): Observable<NetworkResponse<Poem>> = Observable.empty()

    suspend fun getPoemByIdCr(poemId: String, userId: String?) =
        Networking.doCall(unprotected?.getPoemById(poemId, userId)!!)

    @JvmStatic
    fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>): Observable<NetworkResponse<Poem>> =
        Observable.empty()
    //</editor-fold>

    //<editor-fold desc="Categories">
    @JvmStatic
    fun getAllCategories(): Observable<NetworkResponse<JsonArray>> = Observable.empty()

    suspend fun getAllCategoriesCr() = Networking.doCall(unprotected?.getCategories()!!)
    //</editor-fold>

    //<editor-fold desc="Auth">
    @JvmStatic
    fun loginUser(email: String, password: String): Observable<AccessToken> = Observable.empty()

    @JvmStatic
    fun registerUser(userName: String, email: String, password: String): Observable<AccessToken> = Observable.empty()

    @JvmStatic
    fun getCurrentUser(): Observable<NetworkResponse<JsonObject>> = Observable.empty()
    //</editor-fold>

    //<editor-fold desc="Reviews">
    @JvmStatic
    fun getAllReviewsForPoem(poemId: String): Observable<NetworkResponse<JsonArray>> = Observable.empty()

    @JvmStatic
    fun getOwnReviewForPoem(poemId: String, userId: String?): Observable<NetworkResponse<JsonArray>> =
        Observable.empty()

    suspend fun getOwnReviewForPoemCr(poemId: String, userId: String?) =
        Networking.doCall(unprotected?.getOwnReviewForPoem(poemId, userId)!!)

    @JvmStatic
    suspend fun createReviewCr(poemId: String, reviewText: String, reviewRating: Float) =
        Networking.doCall(protected?.createReview(poemId, reviewText, reviewRating)!!)

    @JvmStatic
    suspend fun updateReviewCr(reviewId: String, reviewText: String, reviewRating: Float) =
        Networking.doCall(protected?.editReview(reviewId, reviewText, reviewRating)!!)

    @JvmStatic
    suspend fun deleteReviewCr(reviewId: String) =
        Networking.doCall(protected?.deleteReview(reviewId)!!)
    //</editor-fold>
}