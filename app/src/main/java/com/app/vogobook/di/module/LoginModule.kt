package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.presenter.LoginPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.LoginActivity
import com.app.vogobook.view.ui.callback.LoginView
import com.app.vogobook.viewmodel.UserViewModel
import com.app.vogobook.viewmodel.UserViewModelImpl
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

    @Provides
    @ActivityScope
    fun provideUserViewModel(context: Context, disposableManager: DisposableManager, bookService: BookService, sessionManager: SessionManager) : UserViewModel = UserViewModelImpl(context, bookService, disposableManager, sessionManager)

    @Provides
    @ActivityScope
    fun provideLoginPresenter(userViewModel : UserViewModel) : LoginPresenter =  LoginPresenterImpl(view, userViewModel)

    @Provides
    @ActivityScope
    fun provideVogoLoadingDialog() : VogoLoadingDialog = VogoLoadingDialog(activity)
}