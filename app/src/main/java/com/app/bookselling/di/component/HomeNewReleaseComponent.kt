package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeCommonModule
import com.app.bookselling.di.module.HomeNewReleaseModule
import com.app.bookselling.di.module.HomeTopSellingModule
import com.app.bookselling.di.scope.FragmentScope
import com.app.bookselling.di.scope.SubFragmentScope
import com.app.bookselling.view.ui.fragment.home.HomeFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeNewReleaseFragment
import com.app.bookselling.view.ui.fragment.home.tabs.HomeTopSellingFragment
import dagger.Subcomponent


@SubFragmentScope
@Subcomponent(modules = [HomeNewReleaseModule::class])
interface HomeNewReleaseComponent {
    fun inject(homeNewReleaseFragment: HomeNewReleaseFragment) : HomeNewReleaseFragment
}