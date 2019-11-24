package com.app.vogobook.view.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.navigation.NavController
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class WriteReviewFragment : BaseFragment() {

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @BindView(R.id.rating_bar)
    lateinit var ratingBar: RatingBar

    @BindView(R.id.edit_text_review)
    lateinit var editTextReview: EditText


    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_write_review, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .inject(this)
    }

    override fun initAttributes() {
        editTextReview.requestFocus()
        mBottomNavigation.setOnNavigationItemSelectedListener(mActivity)
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = "Write Review"


        mBottomNavigation.visibility = View.GONE
    }

    @OnClick(R.id.rating_bar)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.rating_bar -> {
                Toast.makeText(context, ratingBar.rating.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}