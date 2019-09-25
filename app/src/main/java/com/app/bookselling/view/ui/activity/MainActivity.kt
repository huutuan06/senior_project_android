package com.app.bookselling.view.ui.activity

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.service.model.Config
import com.app.bookselling.view.ui.callback.MainView
import com.app.bookselling.view.ui.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {


    private lateinit var mBottomNavigation: BottomNavigationView
    private lateinit var mNavHostFragment: HomeFragment
    private lateinit var mNavController: NavController

    var mContext: Context? = null
        @Inject set

    var mMainPresenter: MainPresenter? = null
        @Inject set

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    override fun initAttributes() {
//        txtDemo!!.text = intent.extras!!.getString("Name")

    }

    override fun initViews() {
//        val homeFragment = HomeFragment()
//        openFragment(homeFragment)
//       onClickBottomNavigation()
        mBottomNavigation = findViewById(R.id.bottom_navigation_bar)

        onClickBottomNavigation()
        mNavHostFragment = supportFragmentManager.findFragmentById(R.id.homeFragment) as HomeFragment
        mNavController = NavHostFragment.findNavController(mNavHostFragment)
        mNavController.setGraph(R.navigation.navigation_graph)

    }

    public override val layoutRes: Int
        get() = R.layout.activity_main

    override fun setDisposable(disposable: Disposable) {
        disposable.isDisposed
    }

    override fun loadAppConfigurationSuccess(config: Config) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAppConfigurationFailure(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun onClickBottomNavigation() {
        mBottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_home -> {
                    mNavController.popBackStack()
                    mNavController.navigate(R.id.homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_item_personal -> {
                    mNavController.popBackStack()
                    mNavController.navigate(R.id.personalFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

//        bottomNavigation = findViewById(R.id.bottom_navigation_bar)
//        bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_item_home -> {
//                    val homeFragment = HomeFragment()
//                    openFragment(homeFragment)
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menu_item_personal -> {
//                    val personalFragment = PersonalFragment()
//                    openFragment(personalFragment)
//                    return@setOnNavigationItemSelectedListener true
//                }
//            }
//            false
//
//        }
    }

//    private fun openFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
//                .commit()
//    }

}
