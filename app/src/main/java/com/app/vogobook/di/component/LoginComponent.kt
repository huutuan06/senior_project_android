package com.app.vogobook.di.component

import com.app.vogobook.di.module.LoginModule
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.view.ui.activity.LoginActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity): LoginActivity
}