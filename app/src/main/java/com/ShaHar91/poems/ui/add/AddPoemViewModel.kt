package com.shahar91.poems.ui.add

import be.appwise.core.extensions.logging.loge
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.ui.base.BaseGoogleViewModel

class AddPoemViewModel : BaseGoogleViewModel() {
    var checkedCategories: List<Category> = emptyList()

    fun getAllCategories(onSuccess: (List<Category>) -> Unit) {
        CategoryRepository.getCategories({
            onSuccess(it)
        }, {
            loge(null, it, "")
        })
    }

    fun addNewPoem(poemTitle: String, poemBody: String, onSuccess: () -> Unit,
        onError: (Throwable) -> Unit) {
        PoemRepository.createPoem(poemTitle, poemBody, checkedCategories.map { it._id }, onSuccess, onError)
    }
}