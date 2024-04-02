package com.shahar91.poems.ui.home.poemsPerCategoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.domain.repository.IPoemRepository
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

    init {
        getAllPoemsForCategoryId()
    }

    fun getAllPoemsForCategoryId() = launchAndLoad {
        poemRepository.fetchPoemsForCategory(categoryId)
        setIsRefreshing(false)
    }
}