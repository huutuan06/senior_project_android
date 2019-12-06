package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.navigation.NavController
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
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

    @BindView(R.id.image_book)
    lateinit var imgBook: ImageView

    @BindView(R.id.text_view_book_title)
    lateinit var title: TextView

    @BindView(R.id.text_view_book_author)
    lateinit var author: TextView

    @BindView(R.id.text_view_book_price)
    lateinit var price: TextView

    var book: Book? = null

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

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mBottomNavigation.setOnNavigationItemSelectedListener(mActivity)
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = "Write Review"
        mBottomNavigation.visibility = View.GONE

        book = arguments!!.getParcelable(Constants.BOOK)
        Picasso.get().load(book!!.image).resize(Resources.getSystem().displayMetrics.widthPixels ,  Resources.getSystem().displayMetrics.widthPixels*3/2)
            .centerCrop().into(imgBook)
        title.text = book!!.title.toString()
        author.text = book!!.author.toString()
        price.text = "$" + book!!.price.toString()
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