package com.app.bookselling.view.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import butterknife.BindView
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.view.ui.callback.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView,
        BottomNavigationView.OnNavigationItemSelectedListener, NavController.OnDestinationChangedListener {

    @Inject lateinit var mContext: Context

    @Inject lateinit var mNavController: NavController

    @Inject lateinit var mMainPresenter: MainPresenter

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @BindView(R.id.bottom_navigation_bar)
    @JvmField var mBottomNavigation: BottomNavigationView? = null

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mBottomNavigation!!.setOnNavigationItemSelectedListener(this)
        mNavController.setGraph(R.navigation.navigation_graph)
        mNavController.addOnDestinationChangedListener(this)
        mToolbar.title = "Book Selling Online"
    }

    public override val layoutRes: Int
        get() = R.layout.activity_main

    override fun setDisposable(disposable: Disposable) {
        disposable.isDisposed
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.label.toString()) {
            "homeFragment" -> {
                Toast.makeText(mContext, "Home", Toast.LENGTH_SHORT).show()
            }
            "personalFragment" -> {
                Toast.makeText(mContext, "Personal", Toast.LENGTH_SHORT).show()
            }
            "homeCommonFragment" -> {
                Toast.makeText(mContext, "homeCommon", Toast.LENGTH_SHORT).show()
            }
            "homeTopSellingFragment" -> {
                Toast.makeText(mContext, "homeTopSellingFragment", Toast.LENGTH_SHORT).show()
            }
            "homeReleaseFragment" -> {
                Toast.makeText(mContext, "homeReleaseFragment", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

}
