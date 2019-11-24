package com.app.vogobook.di.component

import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.module.HomeModule
import com.app.vogobook.di.module.HomeTopSellingModule
import com.app.vogobook.di.module.HomeNewReleaseModule
import com.app.vogobook.di.scope.FragmentScope
import com.app.vogobook.view.ui.fragment.BookDetailFragment
import com.app.vogobook.view.ui.fragment.home.HomeFragment
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