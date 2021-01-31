package com.shahar91.poems.ui.add

import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class AddPoemViewModel : PoemBaseViewModel() {
    var checkedCategories: List<Category> = emptyList()
    var categoriesLive = CategoryRepository.getAllCategoriesLive()

    fun getAllCategoriesCr() = launchAndLoad {
        CategoryRepository.getCategories()
    }

    fun addNewPoem(poemTitle: String, poemBody: String, onSuccess: () -> Unit) = launchAndLoad {
        PoemRepository.createPoem(poemTitle, poemBody, checkedCategories.map { it._id })
        onSuccess()
    }
}