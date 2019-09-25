package com.app.bookselling.di.module

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.bookselling.R
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.presenter.MainPresenterImpl
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.callback.MainView
import dagger.Module
import dagger.Provides

@Module
class MainModule() {

    constructor(private val activity: MainActivity, private val view: MainView)

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
    fun provideMainPresenter(): MainPresenter {
        return MainPresenterImpl()
    }

    @Provides
    @ActivityScope
    fun provideNavHostFragment() : NavHostFragment = activity.supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment

    @Provides
    @ActivityScope
    fun provideNavController(navHostFragment: NavHostFragment) : NavController = NavHostFragment.findNavController(navHostFragment)
}