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
        PoemRepository.createPoem(poemTitle, poemBody, checkedCategories.map { it.id }, onSuccess, onError)
    }
}