package com.app.bookselling.view.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.presenter.MainPresenter
import com.app.bookselling.service.model.Config
import com.app.bookselling.view.ui.callback.MainView
import com.app.bookselling.view.ui.fragment.HomeFragment
import com.app.bookselling.view.ui.fragment.PersonalFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {


    var mContext: Context? = null
        @Inject set

    var mMainPresenter: MainPresenter? = null
        @Inject set

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this, this)).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
//        txtDemo!!.text = intent.extras!!.getString("Name")
       onClickBottomNavigation()
    }

    override fun initViews() {
        val homeFragment = HomeFragment()
        openFragment(homeFragment)
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
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_home -> {
                    val homeFragment = HomeFragment()
                    openFragment(homeFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_item_personal -> {
                    val personalFragment = PersonalFragment()
                    openFragment(personalFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
                .commit()
    }

}
