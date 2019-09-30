package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.ProfileModule
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.home.tabs.HomeCommonDetailFragment

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun plus(homeModule: HomeModule) : HomeComponent
    fun plus(profileModule: ProfileModule) : ProfileComponent

    fun inject(activity: MainActivity): MainActivity
    fun inject(homeCommonDetailFragment: HomeCommonDetailFragment) : HomeCommonDetailFragment
}