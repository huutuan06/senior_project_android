package com.app.vogobook.di.component

import com.app.vogobook.di.module.SplashModule
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(activity: SplashActivity): SplashActivity
}