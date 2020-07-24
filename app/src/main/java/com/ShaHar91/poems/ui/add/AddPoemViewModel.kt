package com.shahar91.poems.ui.add

import be.appwise.core.extensions.logging.loge
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.redux.AppState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.yheriatovych.reductor.Store
import javax.inject.Inject

class AddPoemViewModel @Inject internal constructor(store: Store<AppState>) : BaseGoogleViewModel(store) {
    lateinit var categories: List<Category>

    fun getAllCategories(onSuccess: (List<Category>) -> Unit) {
        CategoryRepository.getCategories({
            categories = it
            onSuccess(it)
        }, {
            loge(null, it, "")
        })
    }

    fun addNewPoem(poemTitle: String, poemBody: String, categoryList: List<String>, onSuccess: () -> Unit,
        onError: (Throwable) -> Unit) {
        PoemRepository.createPoem(poemTitle, poemBody, categoryList, onSuccess, onError)
    }
}