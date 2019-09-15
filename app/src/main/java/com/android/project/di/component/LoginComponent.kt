package com.android.project.di.component

import com.android.project.di.module.LoginModule
import com.android.project.di.scope.ActivityScope
import com.android.project.view.ui.activity.LoginActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity): LoginActivity
}