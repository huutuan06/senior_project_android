package com.app.bookselling.di.component

import com.app.bookselling.di.module.LoginModule
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.LoginActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity): LoginActivity
}