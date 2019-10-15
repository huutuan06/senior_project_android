package com.app.bookselling.di.module

import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.bookselling.R
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.presenter.MainPresenterImpl
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.callback.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    private var activity: MainActivity? = null
    private var view: MainView? = null

    // Default Constructor
    constructor(activity: MainActivity, view: MainView)  {
        this.activity = activity
        this.view = view
    }

    constructor(activity: MainActivity)  {
        this.activity = activity
    }

    @Provides
    @ActivityScope
    fun provideActivity(): MainActivity {
        return activity!!
    }

    @Provides
    @ActivityScope
    fun provideView(): MainView {
        return view!!
    }

    @Provides
    @ActivityScope
    fun provideMainPresenter(): MainPresenter {
        return MainPresenterImpl()
    }

    @Provides
    @ActivityScope
    fun provideNavHostFragment() : NavHostFragment = activity!!.supportFragmentManager.findFragmentById(R.id.fragment_nav_host) as NavHostFragment

    @Provides
    @ActivityScope
    fun provideNavController(/*@Named("Main") */navHostFragment: NavHostFragment) : NavController = NavHostFragment.findNavController(navHostFragment)

    @Provides
    @ActivityScope
    fun provideBottomNavigation() :  BottomNavigationView = activity!!.findViewById(R.id.bottom_navigation_bar) as BottomNavigationView

    @Provides
    @ActivityScope
    fun provideCreateToolbar(): androidx.appcompat.widget.Toolbar = activity!!.findViewById(R.id.toolbar_main) as androidx.appcompat.widget.Toolbar



}