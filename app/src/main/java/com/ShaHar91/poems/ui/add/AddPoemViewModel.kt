package com.shahar91.poems.ui.add

import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.domain.model.Category
import com.shahar91.poems.domain.repository.ICategoryRepository
import com.shahar91.poems.domain.repository.IPoemRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel

class AddPoemViewModel(
    private val categoryRepository: ICategoryRepository,
    private val poemRepository: IPoemRepository
) : PoemBaseViewModel() {
    var checkedCategories: List<Category> = emptyList()
    var categoriesLive = categoryRepository.findAllLive()

    val poemTitle = MutableLiveData("")
    val poemBody = MutableLiveData("")

    init {
        getAllCategories()
    }

    fun getAllCategories() = launchAndLoad {
        categoryRepository.fetchCategories()
    }

    fun addNewPoem(poemTitle: String, poemBody: String, onSuccess: () -> Unit) = launchAndLoad {
        poemRepository.createPoem(poemTitle, poemBody, checkedCategories.map { it.id })
        onSuccess()
    }
}