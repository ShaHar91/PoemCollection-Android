package com.shahar91.poems.ui.home.categories

import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.ui.base.BaseGoogleViewModel

class CategoryViewModel : BaseGoogleViewModel() {
    var categories: List<Category> = emptyList()
        private set

    fun getAllCategories(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) {
        CategoryRepository.getCategories(
            {
                this.categories = it
                onSuccess(it)
            }, {
                onError(it)
            })
    }
}