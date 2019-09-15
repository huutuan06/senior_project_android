package com.android.project.di.module

import android.content.Context
import com.android.project.di.scope.ActivityScope
import com.android.project.presenter.MainPresenter
import com.android.project.presenter.MainPresenterImpl
import com.android.project.service.connect.rx.DisposableManager
import com.android.project.service.repository.BookService
import com.android.project.view.ui.activity.MainActivity
import com.android.project.view.ui.callback.MainView
import com.android.project.viewmodel.ConfigVMImpl
import com.android.project.viewmodel.ConfigureVM
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
    fun provideConfigViewMode(context: Context, apiService: BookService, disposableManager: DisposableManager): ConfigureVM {
        return ConfigVMImpl(context, apiService, disposableManager)
    }

}