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

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@Module
class MainModule(private val activity: MainActivity, private val view: MainView) {


    @Provides
    @ActivityScope
    internal fun provideActivity(): MainActivity {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideView(): MainView {
        return view
    }

    @Provides
    @ActivityScope
    internal fun provideMainPresenter(view: MainView, model: ConfigureVM): MainPresenter {
        return MainPresenterImpl(view, model)
    }

    @Provides
    @ActivityScope
    internal fun provideConfigViewMode(context: Context, apiService: BookService, disposableManager: DisposableManager): ConfigureVM {
        return ConfigVMImpl(context, apiService, disposableManager)
    }

}