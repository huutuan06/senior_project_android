package com.app.vogobook.di.component

import com.app.vogobook.di.module.HomeNewReleaseModule
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.view.ui.fragment.home.tabs.HomeNewReleaseFragment
import dagger.Subcomponent


@SubFragmentScope
@Subcomponent(modules = [HomeNewReleaseModule::class])
interface HomeNewReleaseComponent {
    fun inject(homeNewReleaseFragment: HomeNewReleaseFragment) : HomeNewReleaseFragment
}