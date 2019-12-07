package com.app.vogobook.di.module

import android.content.Context
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.LoginPresenter
import com.app.vogobook.presenter.LoginPresenterImpl
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.LoginActivity
import com.app.vogobook.view.ui.callback.LoginView
import com.app.vogobook.viewmodel.LoginModel
import com.app.vogobook.viewmodel.LoginModelImpl
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
    fun provideUserViewModel(context: Context, activity: LoginActivity,disposableManager: DisposableManager, bookService: BookService, sessionManager: SessionManager, roomUIManager: RoomUIManager) : LoginModel = LoginModelImpl(context, activity, bookService, disposableManager, sessionManager, roomUIManager)

    @Provides
    @ActivityScope
    fun provideLoginPresenter(loginModel : LoginModel) : LoginPresenter =  LoginPresenterImpl(view, loginModel)

    @Provides
    @ActivityScope
    fun provideVogoLoadingDialog() : VogoLoadingDialog = VogoLoadingDialog(activity)
}