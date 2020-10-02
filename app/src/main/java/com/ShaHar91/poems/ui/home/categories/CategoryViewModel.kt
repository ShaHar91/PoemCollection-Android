package com.shahar91.poems.ui.home.categories

import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class CategoryViewModel : PoemBaseViewModel() {
    var categoriesLive = CategoryRepository.getAllCategoriesLive()

    fun getAllCategoriesCr() = launchAndLoad {
        CategoryRepository.getCategoriesCr()
    }
}