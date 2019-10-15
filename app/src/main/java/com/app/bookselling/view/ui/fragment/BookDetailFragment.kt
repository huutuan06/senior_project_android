package com.app.bookselling.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        mBottomNavigation.visibility = View.GONE
    }

}