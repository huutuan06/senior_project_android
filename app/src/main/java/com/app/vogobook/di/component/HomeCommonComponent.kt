package com.app.vogobook.di.component

import com.app.vogobook.di.module.HomeCommonModule
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.view.ui.fragment.home.tabs.HomeCommonFragment
import dagger.Subcomponent


@SubFragmentScope
@Subcomponent(modules = [HomeCommonModule::class])
interface HomeCommonComponent {
    fun inject(homeCommonFragment: HomeCommonFragment) : HomeCommonFragment
}