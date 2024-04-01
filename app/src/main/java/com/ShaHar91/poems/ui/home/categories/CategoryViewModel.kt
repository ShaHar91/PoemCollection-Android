package com.shahar91.poems.ui.home.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : PoemBaseViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::CategoryViewModel)
    }

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing
    fun setIsRefreshing(refreshing: Boolean) {
        _isRefreshing.postValue(refreshing)
    }

    var categoriesLive = categoryRepository.findAllLive()

    fun getAllCategories() = launchAndLoad {
        categoryRepository.getCategories()
        setIsRefreshing(false)
    }
}