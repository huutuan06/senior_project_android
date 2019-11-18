package com.app.bookselling.di.module

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.bookselling.R
import com.app.bookselling.di.scope.ActivityScope
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.presenter.MainPresenterImpl
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.callback.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.dialog_add_to_cart.view.*

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

    @Provides
    @ActivityScope
    fun provideTabLayout(): LinearLayout = activity!!.findViewById(R.id.tablayout_book_list) as LinearLayout

    @SuppressLint("RestrictedApi")
    @Provides
    @ActivityScope
    fun provideSnackBarLayout(context: Context): Snackbar.SnackbarLayout {
        return Snackbar.SnackbarLayout(context)
    }

    @Provides
    @ActivityScope
    fun provideSnackBar(snackbarLayout: Snackbar.SnackbarLayout): Snackbar {
        val parentLayout : View? = activity?.window?.decorView?.findViewById(android.R.id.content)
        val snackbar :Snackbar = Snackbar.make(parentLayout!!, "File Choosers", Snackbar.LENGTH_LONG)
        val layout: Snackbar.SnackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        val params: FrameLayout.LayoutParams = layout.layoutParams as FrameLayout.LayoutParams
        params.width = FrameLayout.LayoutParams.MATCH_PARENT
        params.gravity = Gravity.CENTER or Gravity.BOTTOM
        layout.layoutParams = params
//        val constraintLayout: ConstraintLayout = layout.findViewById(android.support.design.R.id.snackbar_add_to_cart)
//        constraintLayout.visibility = View.INVISIBLE
        if (snackbarLayout.parent != null)
            (snackbarLayout.parent as ViewGroup).removeView(snackbarLayout)
        layout.addView(snackbarLayout)
        snackbar.duration = BaseTransientBottomBar.LENGTH_INDEFINITE
        return snackbar
    }
}