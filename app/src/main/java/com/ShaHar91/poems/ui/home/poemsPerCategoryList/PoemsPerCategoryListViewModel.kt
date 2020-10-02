package com.shahar91.poems.ui.home.poemsPerCategoryList

import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class PoemsPerCategoryListViewModel(categoryId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemsPerCategoryListViewModel)
    }

    var allPoemsForCategoryLive = PoemRepository.getPoemsForCategoryLive(categoryId)

    fun getAllPoemsForCategoryCr(categoryId: String) = launchAndLoad {
        PoemRepository.getPoemsForCategoryCr(categoryId)
    }
}