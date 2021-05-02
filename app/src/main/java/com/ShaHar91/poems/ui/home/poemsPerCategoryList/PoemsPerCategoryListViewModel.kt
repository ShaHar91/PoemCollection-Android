package com.shahar91.poems.ui.home.poemsPerCategoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import com.orhanobut.logger.Logger
import com.shahar91.poems.MyApp
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.ui.base.PoemBaseViewModel

class PoemsPerCategoryListViewModel(private val categoryId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemsPerCategoryListViewModel)
    }

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing
    fun setIsRefreshing(refreshing: Boolean) {
        _isRefreshing.postValue(refreshing)
    }

    var allPoemsForCategoryLive = MyApp.poemRepository.getPoemsForCategoryLive(categoryId)

    fun getAllPoemsForCategoryId() = launchAndLoad {
        MyApp.poemRepository.getPoemsForCategory(categoryId)
    }

    fun getPoemsWithRelations(poemId: String) = launchAndLoad {
        MyApp.poemRepository.getPoemById(poemId)

//        val poemsWithRelations = MyApp.poemRepository.getPoemWithRelations()
//        Logger.d(poemsWithRelations)
    }
}