package com.app.bookselling.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BookDetailFragment : BaseFragment() {

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mTabLayout: LinearLayout

//    private lateinit var ratingBar: RatingBar
    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity)).inject(this)
    }

    override fun initAttributes() {
//        mToolbar.setNavigationIcon(R.drawable.ic_toolbar_back)
//        ratingBar = findViewById(R.id.rating_bar)
//        ratingBar.rating
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
//        mActivity.onSupportNavigateUp()
        mBottomNavigation.visibility = View.GONE
        mTabLayout.visibility = View.GONE
    }
}