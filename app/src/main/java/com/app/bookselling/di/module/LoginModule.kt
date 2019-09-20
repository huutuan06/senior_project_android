package com.app.bookselling.di.module

import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.LoginActivity
import com.app.bookselling.view.ui.callback.LoginView
import com.facebook.CallbackManager
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val activity: LoginActivity, private val view: LoginView) {


    @Provides
    @ActivityScope
    fun provideActivity(): LoginActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideView(): LoginView {
        return view
    }

    @Provides
    @ActivityScope
    fun provideCallbackManager() : CallbackManager = CallbackManager.Factory.create()

}