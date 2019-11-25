package com.app.vogobook.view.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class BookDetailFragment : BaseFragment(), CartSnackBarLayout.CartSnackBarLayoutInterface {

    @Inject lateinit var mContext: Context

    @Inject lateinit var mActivity: MainActivity

    @Inject lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject lateinit var mBottomNavigation: BottomNavigationView

    @Inject lateinit var mSnackbar: Snackbar

    @Inject lateinit var mCartSnackBarLayout: CartSnackBarLayout

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
        mCartSnackBarLayout.attachDialogInterface(this)
        mToolbar.title = "Book Selling Online"
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE
    }

    @OnClick(R.id.button_write_review, R.id.button_add_to_cart)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_write_review -> {
                mActivity.mNavController.navigate(R.id.writeReviewFragment)
            }
            R.id.button_add_to_cart -> {
                mSnackbar.show()
            }
        }
    }

    override fun hello(text: String) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
        mSnackbar.dismiss()
    }

    override fun dismissSnackbar() {
        mSnackbar.dismiss()
    }

    override fun onPause() {
        super.onPause()
        mSnackbar.dismiss()
    }
}