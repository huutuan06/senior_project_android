package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.presenter.LoginPresenter
import com.app.bookselling.presenter.LoginPresenterImpl
import com.app.bookselling.service.connect.rx.DisposableManager
import com.app.bookselling.service.repository.BookService
import com.app.bookselling.view.ui.activity.LoginActivity
import com.app.bookselling.view.ui.callback.LoginView
import com.app.bookselling.viewmodel.UserViewModel
import com.app.bookselling.viewmodel.UserViewModelImpl
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
    fun provideUserVM(context: Context, disposableManager: DisposableManager, bookService: BookService) : UserViewModel = UserViewModelImpl(context, bookService, disposableManager)

    @Provides
    @ActivityScope
    fun provideLoginPresenter(userViewModel : UserViewModel) : LoginPresenter =  LoginPresenterImpl(view, userViewModel)

}