package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun plus(homeCommonModule: HomeCommonModule) : HomeCommonComponent

    fun inject(homeFragment: HomeFragment): HomeFragment
    fun inject(homeCommonFragment: HomeCommonFragment): HomeCommonFragment
}