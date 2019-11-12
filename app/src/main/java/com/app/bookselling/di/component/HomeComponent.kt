package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.HomeTopSellingModule
import com.app.bookselling.di.module.HomeNewReleaseModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.BookDetailFragment
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun plus(homeCommonModule: HomeCommonModule) : HomeCommonComponent
    fun plus(homeTopSellingModule: HomeTopSellingModule) : HomeTopSellingComponent
    fun plus(homeNewReleaseModule: HomeNewReleaseModule) : HomeNewReleaseComponent

    fun inject(homeFragment: HomeFragment): HomeFragment
    fun inject(bookDetailFragment: BookDetailFragment): BookDetailFragment
}