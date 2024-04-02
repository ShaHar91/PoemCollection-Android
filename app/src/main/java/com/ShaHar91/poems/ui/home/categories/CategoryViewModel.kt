package com.shahar91.poems.ui.home.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.domain.repository.ICategoryRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class CategoryViewModel(
    private val categoryRepository: ICategoryRepository
) : PoemBaseViewModel() {

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing
    fun setIsRefreshing(refreshing: Boolean) {
        _isRefreshing.postValue(refreshing)
    }

    var categoriesLive = categoryRepository.findAllLive()

    init {
        getAllCategories()
    }

    fun getAllCategories() = launchAndLoad {
        categoryRepository.fetchCategories()
        setIsRefreshing(false)
    }
}