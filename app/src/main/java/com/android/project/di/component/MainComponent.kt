package com.android.project.di.component

import com.android.project.di.module.MainModule
import com.android.project.di.scope.ActivityScope
import com.android.project.view.ui.activity.MainActivity

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity): MainActivity
}


