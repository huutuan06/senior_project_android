package com.app.bookselling.view.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
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

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        mNavController.setGraph(R.navigation.navigation_graph)
        mNavController.addOnDestinationChangedListener(this)
        mToolbar.title = "Book Selling Online"
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        visibleIconInFragments(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_item_cart -> {
                mNavController.navigate(R.id.cartFragment)
                return true
            }
            R.id.menu_item_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.label.toString()) {
            mContext.getString(R.string.label_home) -> {
//                Toast.makeText(mContext, "Home", Toast.LENGTH_SHORT).show()
            }
            mContext.getString(R.string.label_personal) -> {
//                Toast.makeText(mContext, "Personal", Toast.LENGTH_SHORT).show()
            }
            mContext.getString(R.string.label_cart) -> {
                /**
                 * When you switch to CardFragment.
                 * YOu can hidden icon what you want.
                 * and call method invalidateOptionsMenu
                 * I will be update automatically in MainActivity.
                 * Let try it.
                 */
                invalidateOptionsMenu()
            }
            mContext.getString(R.string.label_manage_orders) -> {
                invalidateOptionsMenu()
            }
            mContext.getString(R.string.label_write_review) -> {
                invalidateOptionsMenu()
            }
            mContext.getString(R.string.label_book_detail) -> {
                invalidateOptionsMenu()
            }
            mContext.getString(R.string.label_account) -> {
                invalidateOptionsMenu()
            }
        }
    }

    private fun visibleIconInFragments(menu: Menu?) {
        // Get all MenuItem from Toolbar and customize it.
        val searchItem = menu!!.findItem(R.id.menu_item_search) as MenuItem
        val cartItem = menu.findItem(R.id.menu_item_cart) as MenuItem
        when(mNavController.currentDestination!!.label) {
            mContext.getString(R.string.label_home) -> {
                // TODO what you want
            }
            mContext.getString(R.string.label_personal) -> {
                searchItem.isVisible = false
                cartItem.isVisible = true
            }
            mContext.getString(R.string.label_cart) -> {
                // TODO what you want
                // I assume that I want to hidden search when go to cardFragment.
                // We you tab CardFragment. onDestinationChanged will be called
                // After call invalidateOptionsMenu and allow specify exactly fragment are in nav controller do you job.
                searchItem.isVisible = false
                cartItem.isVisible = false
            }
            mContext.getString(R.string.label_manage_orders) -> {
                searchItem.isVisible = false
                cartItem.isVisible = false
            }
            mContext.getString(R.string.label_write_review) -> {
                searchItem.isVisible = false
                cartItem.isVisible = false
            }
            mContext.getString(R.string.label_book_detail) -> {
                searchItem.isVisible = true
                cartItem.isVisible = true
            }
            mContext.getString(R.string.label_account) -> {
                searchItem.isVisible = false
                cartItem.isVisible = false
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
