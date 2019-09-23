package com.app.bookselling.di.module

import android.content.Context
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.presenter.MainPresenterImpl
import com.app.bookselling.service.connect.rx.DisposableManager
import com.app.bookselling.service.repository.BookService
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.callback.MainView
import com.app.bookselling.viewmodel.ConfigVMImpl
import com.app.bookselling.viewmodel.ConfigureVM
import com.app.bookselling.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity, private val view: MainView) {


    @Provides
    @ActivityScope
    fun provideActivity(): MainActivity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideView(): MainView {
        return view
    }

    @Provides
    @ActivityScope
    fun provideMainPresenter(view: MainView, model: ConfigureVM): MainPresenter {
        return MainPresenterImpl(view, model)
    }

    @Provides
    @ActivityScope
    fun provideConfigViewModel(context: Context, apiService: BookService, disposableManager: DisposableManager): ConfigureVM {
        return ConfigVMImpl(context, apiService, disposableManager)
    }

}