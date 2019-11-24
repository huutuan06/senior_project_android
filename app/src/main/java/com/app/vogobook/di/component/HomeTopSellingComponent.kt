package com.app.vogobook.di.component

import com.app.vogobook.di.module.HomeTopSellingModule
import com.app.vogobook.di.scope.SubFragmentScope
import com.app.vogobook.view.ui.fragment.home.tabs.HomeTopSellingFragment
import dagger.Subcomponent


@SubFragmentScope
@Subcomponent(modules = [HomeTopSellingModule::class])
interface HomeTopSellingComponent {
    fun inject(homeTopSellingFragment: HomeTopSellingFragment) : HomeTopSellingFragment
}