package com.shahar91.poems.ui.add

import com.shahar91.poems.MyApp
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.PoemBaseViewModel

class AddPoemViewModel : PoemBaseViewModel() {
    var checkedCategories: List<Category> = emptyList()
    var categoriesLive = MyApp.categoryRepository.getAllCategoriesLive()

    fun getAllCategoriesCr() = launchAndLoad {
        MyApp.categoryRepository.getCategories()
    }

    fun addNewPoem(poemTitle: String, poemBody: String, onSuccess: () -> Unit) = launchAndLoad {
        MyApp.poemRepository.createPoem(poemTitle, poemBody, checkedCategories.map { it.id })
        onSuccess()
    }
}