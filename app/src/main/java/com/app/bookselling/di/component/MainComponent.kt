package com.app.bookselling.di.component

import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.module.PersonalModule
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BookDetailFragment

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun plus(homeModule: HomeModule) : HomeComponent
    fun plus(profileModule: PersonalModule) : PersonalComponent

    fun inject(activity: MainActivity): MainActivity
    fun inject(bookDetailFragment: BookDetailFragment) : BookDetailFragment
}