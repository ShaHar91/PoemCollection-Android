package com.shahar91.poems.ui.home.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.MyApp
import com.shahar91.poems.ui.base.PoemBaseViewModel

class CategoryViewModel : PoemBaseViewModel() {

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing
    fun setIsRefreshing(refreshing: Boolean) {
        _isRefreshing.postValue(refreshing)
    }

    var categoriesLive = MyApp.categoryRepository.findAllLive()

    fun getAllCategories() = launchAndLoad {
        MyApp.categoryRepository.getCategories()
        setIsRefreshing(false)
    }
}