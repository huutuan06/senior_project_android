package com.app.bookselling.di.component

import com.app.bookselling.di.module.MainModule
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.MainActivity

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity): MainActivity
}