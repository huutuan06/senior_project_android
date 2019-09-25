package com.app.bookselling.view.ui.fragment


import androidx.viewpager.widget.ViewPager
import com.app.bookselling.R
import com.app.bookselling.view.adapter.BookPagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun distributedDaggerComponents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initAttributes() {
        setupViewPager()
    }


    override fun initViews() {
        viewPager = activity!!.findViewById(R.id.viewpager_book_list)
        tabLayout = activity!!.findViewById(R.id.tablayout_book_list)
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