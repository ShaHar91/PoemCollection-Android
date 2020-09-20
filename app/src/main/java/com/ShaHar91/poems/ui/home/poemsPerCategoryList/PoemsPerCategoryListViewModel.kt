package com.shahar91.poems.ui.home.poemsPerCategoryList

import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.repositories.PoemRepository.getPoems
import com.shahar91.poems.ui.base.PoemBaseViewModel

class PoemsPerCategoryListViewModel : PoemBaseViewModel() {
    var allPoemsForCategory: List<Poem> = emptyList()
        private set

    fun getAllPoemsPerCategory(categoryId: String, onSuccess: (List<Poem>) -> Unit, onError: (Throwable) -> Unit) {
        getPoems(categoryId, {
            this.allPoemsForCategory = it
            onSuccess(it)
        }, {
            onError(it)
        })
    }
}