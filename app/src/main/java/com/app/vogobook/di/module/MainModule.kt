package com.app.vogobook.di.module

import android.R.id
import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.vogobook.R
import com.app.vogobook.di.scope.ActivityScope
import com.app.vogobook.presenter.MainPresenter
import com.app.vogobook.presenter.MainPresenterImpl
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.MainView
import com.app.vogobook.view.ui.fragment.BookDetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
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