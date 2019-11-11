package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.scope.SubFragmentScope
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent


@SubFragmentScope
@Subcomponent(modules = [HomeCommonModule::class])
interface HomeCommonComponent {
    fun inject(homeCommonFragment: HomeCommonFragment) : HomeCommonFragment
}