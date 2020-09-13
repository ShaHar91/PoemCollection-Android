package com.shahar91.poems.ui.home.categories

import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import javax.inject.Inject

class CategoryViewModel @Inject internal constructor() : BaseGoogleViewModel() {
    fun getAllCategories(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) {
        CategoryRepository.getCategories(
            { categories ->
                onSuccess(categories)
            }, { throwable: Throwable ->
                onError(throwable)
            })
    }
}