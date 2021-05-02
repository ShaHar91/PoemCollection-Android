package com.shahar91.poems.ui.home.categories

import com.shahar91.poems.MyApp
import com.shahar91.poems.ui.base.PoemBaseViewModel

class CategoryViewModel : PoemBaseViewModel() {

    var categoriesLive = MyApp.categoryRepository.getAllCategoriesLive()

    fun getAllCategories() = launchAndLoad {
        MyApp.categoryRepository.getCategories()
    }
}