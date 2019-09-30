package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [HomeCommonModule::class])
interface HomeCommonComponent {
    fun inject(homeCommonFragment: HomeCommonFragment) : HomeCommonFragment
}