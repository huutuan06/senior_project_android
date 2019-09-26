package com.app.bookselling.view.ui.fragment


import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.adapter.BookPagerAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class HomeFragment : BaseFragment(), View.OnClickListener {

    var mNavController: NavController? = null
        @Inject set

    override fun onClick(view: View?) {
        when(view!!.id) {
            R.id.text_view_common -> {

            }
            R.id.text_view_top_selling -> {
                mNavController!!.popBackStack()
                mNavController!!.navigate(R.id.homeTopSellingFragment)
            }
            R.id.text_view_new_release -> {
                mNavController!!.popBackStack()
                mNavController!!.navigate(R.id.homeReleaseFragment)
            }
        }
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var tvCommon: TextView
    private lateinit var tvTopSelling: TextView
    private lateinit var tvNewRelease: TextView

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).plus(
                HomeModule(this)).inject(this)
    }

    override fun initAttributes() {
        setupViewPager()
        tvCommon.setOnClickListener(View.OnClickListener {
            mNavController!!.popBackStack()
            mNavController!!.navigate(R.id.homeCommonFragment)
        })
        tvTopSelling.setOnClickListener(this)
        tvNewRelease.setOnClickListener(this)
    }

    override fun initViews() {
        viewPager = activity!!.findViewById(R.id.viewpager_book_list)
        tabLayout = activity!!.findViewById(R.id.tablayout_book_list)
        tvCommon = activity!!.findViewById(R.id.text_view_common)
        tvTopSelling = activity!!.findViewById(R.id.text_view_top_selling)
        tvNewRelease = activity!!.findViewById(R.id.text_view_new_release)
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = BookPagerAdapter(activity!!.supportFragmentManager)
        viewPagerAdapter.addFragment(HomeCommonFragment(), "Common")
        viewPagerAdapter.addFragment(HomeTopSellingFragment(), "Top Selling")
        viewPagerAdapter.addFragment(HomeNewReleaseFragment(), "New Release")

        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)
    }
}