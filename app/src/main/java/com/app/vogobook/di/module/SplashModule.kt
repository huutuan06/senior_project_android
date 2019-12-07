package com.app.vogobook.di.module

import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.SplashActivity
import dagger.Module
import dagger.Provides

@Module
class SplashModule(private val activity: SplashActivity) {

    @Provides
    @ActivityScope
    fun provideActivity(): SplashActivity {
        return activity
    }

}