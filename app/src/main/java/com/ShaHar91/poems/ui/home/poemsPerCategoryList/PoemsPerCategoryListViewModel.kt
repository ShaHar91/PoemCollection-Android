package com.shahar91.poems.ui.home.poemsPerCategoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.data.repositories.IPoemRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class PoemsPerCategoryListViewModel(
    private val categoryId: String,
    private val poemRepository: IPoemRepository
) : PoemBaseViewModel() {

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing
    fun setIsRefreshing(refreshing: Boolean) {
        _isRefreshing.postValue(refreshing)
    }

    var allPoemsForCategoryLive = poemRepository.findAllPoemsForCategoryLive(categoryId)

    fun getAllPoemsForCategoryId() = launchAndLoad {
        poemRepository.getPoemsForCategory(categoryId)
        setIsRefreshing(false)
    }
}