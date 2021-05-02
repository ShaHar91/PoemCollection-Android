package com.shahar91.poems.ui.home.poemsPerCategoryList

import androidx.lifecycle.MutableLiveData
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import com.orhanobut.logger.Logger
import com.shahar91.poems.MyApp
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.ui.base.PoemBaseViewModel

class PoemsPerCategoryListViewModel(categoryId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemsPerCategoryListViewModel)
    }

    var allPoemsForCategoryLive = MutableLiveData(listOf(Poem(id = "5d725a037b292f5f8ceff787"))) // MyApp.poemRepository.getPoemsForCategoryLive(categoryId)

    fun getAllPoemsForCategoryCr(categoryId: String) = launchAndLoad {
        MyApp.poemRepository.getPoemsForCategory(categoryId)
    }

    fun getPoemsWithRelations(poemId: String) = launchAndLoad {
        MyApp.poemRepository.getPoemById(poemId)

//        val poemsWithRelations = MyApp.poemRepository.getPoemWithRelations()
//        Logger.d(poemsWithRelations)
    }
}