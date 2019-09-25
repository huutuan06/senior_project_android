package com.app.bookselling.view.ui.activity

import android.content.Context
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.view.ui.callback.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mBottomNavigation: BottomNavigationView

    var mContext: Context? = null
        @Inject set

    var mNavController: NavController? = null
        @Inject set

    var mMainPresenter: MainPresenter? = null
        @Inject set

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {
    }

    override fun initViews() {
        mBottomNavigation = findViewById(R.id.bottom_navigation_bar)
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        mNavController!!.setGraph(R.navigation.navigation_graph)
    }

    public override val layoutRes: Int
        get() = R.layout.activity_main

    override fun setDisposable(disposable: Disposable) {
        disposable.isDisposed
    }

    override fun onNavigationItemSelected(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.menu_item_home -> {
                mNavController.popBackStack()
                mNavController.navigate(R.id.homeFragment)
                return true
            }
            R.id.menu_item_personal -> {
                mNavController.popBackStack()
                mNavController.navigate(R.id.personalFragment)
                return true
            }
        }
        return false
    }

}
