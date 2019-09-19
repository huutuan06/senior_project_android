package com.app.bookselling.di.module

import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.view.ui.activity.LoginActivity
import com.app.bookselling.view.ui.callback.LoginView
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import dagger.Module
import dagger.Provides
import kotlin.coroutines.coroutineContext

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