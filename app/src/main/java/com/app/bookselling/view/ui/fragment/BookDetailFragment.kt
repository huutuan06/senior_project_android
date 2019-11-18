package com.app.bookselling.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.app.bookselling.R
import com.app.bookselling.app.Application
import com.app.bookselling.di.module.MainModule
import com.app.bookselling.view.custom.CartSnackBarLayout
import com.app.bookselling.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class BookDetailFragment : BaseFragment(), CartSnackBarLayout.CartSnackbarInterface {


    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mSnackbar: Snackbar

    @Inject lateinit var mSnackbarLayout: Snackbar.SnackbarLayout

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
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mToolbar.title = "Book Selling Online"
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE

//        mSnackbarLayout.setInterface(this)
    }

    @OnClick(R.id.button_write_review, R.id.button_add_to_cart)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_write_review -> {
                mActivity.mNavController.navigate(R.id.writeReviewFragment)
            }
            R.id.button_add_to_cart -> {
                showSnackBar()
            }
        }
    }
    override fun showSnackBar() {
        if (mSnackbar.isShown)
            mSnackbar.dismiss()
        else
            mSnackbar.show()
    }
}