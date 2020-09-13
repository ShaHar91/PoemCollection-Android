package com.shahar91.poems.ui.home.categories

import be.appwise.core.extensions.logging.loge
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.redux.AppState
import com.shahar91.poems.redux.state.ViewState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.shahar91.poems.ui.home.categories.redux.CategoryActions
import com.shahar91.poems.ui.home.categories.redux.CategoryState
import com.yheriatovych.reductor.Actions
import com.yheriatovych.reductor.Store
import com.yheriatovych.reductor.rxjava2.RxStore
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class CategoryViewModel @Inject internal constructor(store: Store<AppState>) : BaseGoogleViewModel(store) {
    private var categoryActions = Actions.from(CategoryActions::class.java)

    init {
        getAllCategories()
    }

    fun getAllCategories() {
        CategoryRepository.getCategories(
            { categories ->
                store.dispatch(categoryActions.setCategoryList(
                    ArrayList(categories)))
            }, { throwable: Throwable ->
                throwable.printStackTrace()
                loge(null, throwable, "")
            })
    }

    fun categoriesStateListener(): Observable<List<Category>?> {
        val store = RxStore.asObservable(store)
        return store.map { appState -> appState.viewState() }
            .map { viewState -> viewState.categoryState() }
            .filter { categoryState -> categoryState.categoryList() != null }
            .map { categoryState -> categoryState.categoryList() }
            .distinctUntilChanged()
    }
}