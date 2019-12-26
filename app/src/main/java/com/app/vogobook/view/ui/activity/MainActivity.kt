package com.app.vogobook.view.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.app.vogobook.R
import com.app.vogobook.analytics.VogoAnalytics
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.presenter.MainPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.callback.MainView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener,
    NavController.OnDestinationChangedListener {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mMainPresenter: MainPresenter

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mRoom: RoomUIManager

    @Inject
    lateinit var mFirebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var mVogoAnalytics: VogoAnalytics

    @Inject
    lateinit var mSessionManager: SessionManager

    var user = User()
    var book = Book()
    var bundle = Bundle()

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this)
        mNavController.setGraph(R.navigation.navigation_graph)
        mNavController.addOnDestinationChangedListener(this)
        mToolbar.title = mContext.getString(R.string.app_name)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        mRoom.getUser(object : IRoomListener<User>{
            override fun showListData(t: List<User>) {
                if (t.isNotEmpty())
                    user = t[0]
            }
        })

    }

    override fun onResume() {
        super.onResume()
        if (intent.extras != null) {
            val intent: Intent = this.intent
            bundle = intent.getBundleExtra("Bundle")
            book = bundle.getParcelable(Constants.BOOK)!!
            val bundle = Bundle()
            bundle.putParcelable(Constants.BOOK, book)
            mNavController.navigate(R.id.bookDetailFragment, bundle)
        }
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

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
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
        when (mNavController.currentDestination!!.label) {
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

    override fun onDestroy() {
        mVogoAnalytics.reportDevideInfor(mFirebaseAnalytics, Locale.getDefault().getDisplayLanguage(), System.currentTimeMillis() - mSessionManager.time_using)
        super.onDestroy()
    }
}
