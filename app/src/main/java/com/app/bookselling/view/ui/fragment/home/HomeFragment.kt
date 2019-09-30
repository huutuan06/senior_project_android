package com.app.bookselling.view.ui.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import butterknife.BindView
import butterknife.OnClick
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.ui.activity.MainActivity
import com.app.bookselling.view.ui.fragment.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class HomeFragment : BaseFragment(), NavController.OnDestinationChangedListener {

    @Inject lateinit var mContext: Context

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mFragment: HomeFragment

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar


    @BindView(R.id.bottom_navigation_bar)
    @JvmField var mBottomNavigation: BottomNavigationView? = null

    @BindView(R.id.text_view_common)
    @JvmField var tvCommon : TextView? = null

    @BindView(R.id.text_view_top_selling)
    @JvmField var tvTopSelling : TextView? = null

    @BindView(R.id.text_view_new_release)
    @JvmField var tvNewRelease : TextView? = null

    private lateinit var mNavHostFragment : NavHostFragment
    private lateinit var mNavController : NavController

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
                HomeModule(this)).inject(this)
    }

    override fun initAttributes() {
        mNavHostFragment = mFragment.childFragmentManager.findFragmentById(
            R.id.fragment_nav_host_home) as NavHostFragment
        mNavController = NavHostFragment.findNavController(mNavHostFragment)
        mNavController.setGraph(R.navigation.navigation_graph_home)
        mNavController.addOnDestinationChangedListener(this)
        Application.instance.setCurrentFragment(mFragment)
        mActivity.setSupportActionBar(mToolbar)
        mToolbar.title = "Book Selling Online"

    }

    @OnClick(R.id.text_view_common, R.id.text_view_top_selling, R.id.text_view_new_release)
    fun processEventClick(v : View) {
        when(v.id) {
            R.id.text_view_common -> {
                mNavController.popBackStack()
                val bundle = Bundle()
                bundle.putString("name", "Ben")
                mNavController.navigate(R.id.homeCommonFragment, bundle)
            }
            R.id.text_view_top_selling -> {
                mNavController.popBackStack()
                mNavController.navigate(R.id.homeTopSellingFragment)
            }
            R.id.text_view_new_release -> {
                mNavController.popBackStack()
                mNavController.navigate(R.id.homeNewReleaseFragment)
            }
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.label.toString()) {
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

    public fun getNavController() = mNavController

}