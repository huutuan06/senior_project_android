package com.app.bookselling.view.ui.fragment


import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.HomeModule
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.adapter.BookPagerAdapter
import com.app.bookselling.view.ui.activity.MainActivity
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.a -> {

            }
            R.id.b -> {

            }
            R.id.c -> {

            }
        }
    }

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var tvA: TextView
    private lateinit var tvB: TextView
    private lateinit var tvC: TextView

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun distributedDaggerComponents() {

    }

    override fun initAttributes() {
        setupViewPager()
        tvA.setOnClickListener(this)
        tvB.setOnClickListener(this)
        tvC.setOnClickListener(this)
    }

    override fun initViews() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity, null)).plus(
            HomeModule(this)).inject(this)
        viewPager = activity!!.findViewById(R.id.viewpager_book_list)
        tabLayout = activity!!.findViewById(R.id.tablayout_book_list)
        tvA = activity!!.findViewById(R.id.a)
        tvB = activity!!.findViewById(R.id.b)
        tvC = activity!!.findViewById(R.id.c)
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