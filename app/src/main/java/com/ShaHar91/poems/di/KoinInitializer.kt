package com.shahar91.poems.di

import android.content.Context
import androidx.room.Room
import com.shahar91.poems.data.local.PoemDatabase
import com.shahar91.poems.data.local.TransactionProvider
import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.domain.repository.IAuthRepository
import com.shahar91.poems.domain.repository.ICategoryRepository
import com.shahar91.poems.domain.repository.IPoemRepository
import com.shahar91.poems.domain.repository.IReviewRepository
import com.shahar91.poems.domain.repository.IUserRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.data.repositories.UserRepository
import com.shahar91.poems.data.remote.ProtectedRestClient
import com.shahar91.poems.data.remote.UnProtectedRestClient
import com.shahar91.poems.ui.add.AddPoemViewModel
import com.shahar91.poems.ui.entry.EntryViewModel
import com.shahar91.poems.ui.home.categories.CategoryViewModel
import com.shahar91.poems.ui.home.poem.PoemViewModel
import com.shahar91.poems.ui.home.poemsPerCategoryList.PoemsPerCategoryListViewModel
import com.shahar91.poems.ui.home.profile.ProfileViewModel
import com.shahar91.poems.utils.HawkManager
import com.shahar91.poems.utils.IHawkManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

object KoinInitializer {
    fun init(context: Context) {
        startKoin {
            androidContext(context)

            modules(appModule, daoModule, viewModelModule, repoModule)
        }
    }
}

val appModule = module {
    single<IHawkManager> { HawkManager }

    single { TransactionProvider(get()) }

    single {
        Room
            .databaseBuilder(
                androidContext(),
                PoemDatabase::class.java,
                PoemDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    single(named(DIConstants.NAMED_PROTECTED_SERVICE_AUTH)) { ProtectedRestClient.authService }
    single(named(DIConstants.NAMED_PROTECTED_SERVICE_CATEGORY)) { ProtectedRestClient.categoryService }
    single(named(DIConstants.NAMED_PROTECTED_SERVICE_POEM)) { ProtectedRestClient.poemService }
    single(named(DIConstants.NAMED_PROTECTED_SERVICE_REVIEW)) { ProtectedRestClient.reviewService }
    single(named(DIConstants.NAMED_UNPROTECTED_SERVICE_AUTH)) { UnProtectedRestClient.authService }
    single(named(DIConstants.NAMED_UNPROTECTED_SERVICE_CATEGORY)) { UnProtectedRestClient.categoryService }
    single(named(DIConstants.NAMED_UNPROTECTED_SERVICE_POEM)) { UnProtectedRestClient.poemService }
    single(named(DIConstants.NAMED_UNPROTECTED_SERVICE_REVIEW)) { UnProtectedRestClient.reviewService }
}

val daoModule = module {
    single { get<PoemDatabase>().categoryDao() }
    single { get<PoemDatabase>().userDao() }
    single { get<PoemDatabase>().poemDao() }
    single { get<PoemDatabase>().reviewDao() }
    single { get<PoemDatabase>().poemCategoryCrossRefDao() }
}

val viewModelModule = module {

    viewModelOf(::AddPoemViewModel)
    viewModelOf(::EntryViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::PoemViewModel)
    viewModelOf(::PoemsPerCategoryListViewModel)
    viewModelOf(::ProfileViewModel)
}

val repoModule = module {
    single<IAuthRepository> { AuthRepository(get(named(DIConstants.NAMED_UNPROTECTED_SERVICE_AUTH)), get()) }
    single<ICategoryRepository> { CategoryRepository(get(), get(named(DIConstants.NAMED_UNPROTECTED_SERVICE_CATEGORY))) }
    single<IPoemRepository> { PoemRepository(get(), get(), get(), get(), get(), get(), get(named(DIConstants.NAMED_PROTECTED_SERVICE_POEM)),get(named(DIConstants.NAMED_UNPROTECTED_SERVICE_POEM))) }
    single<IReviewRepository> { ReviewRepository(get(), get(named(DIConstants.NAMED_PROTECTED_SERVICE_REVIEW))) }
    single<IUserRepository> { UserRepository(get(), get(named(DIConstants.NAMED_PROTECTED_SERVICE_AUTH))) }
}