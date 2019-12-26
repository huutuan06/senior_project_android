package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.BookDetailModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.livedata.VogoBookLive
import com.app.vogobook.livedata.`object`.LiveDataBook
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.adapter.BookDetailAdapter
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookDetailView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
import org.w3c.dom.Text
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class BookDetailFragment : BaseFragment(), CartSnackBarLayout.CartSnackBarLayoutInterface,
    BookDetailView {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mSnackbar: Snackbar

    @Inject
    lateinit var mCartSnackBarLayout: CartSnackBarLayout

    @Inject
    lateinit var mPresenter: BookDetailPresenter

    @Inject
    lateinit var mAdapter: BookDetailAdapter

    @Inject
    lateinit var mVogoBookLive: VogoBookLive

    @BindView(R.id.image_book)
    lateinit var imgBook: ImageView

    @BindView(R.id.text_view_book_title)
    lateinit var title: TextView

    @BindView(R.id.text_view_book_author)
    lateinit var author: TextView

    @BindView(R.id.text_view_book_price)
    lateinit var price: TextView

    @BindView(R.id.text_view_book_number_pages)
    lateinit var pages: TextView

    @BindView(R.id.recycler_view_reviews)
    lateinit var rcvReviews: RecyclerView

    @BindView(R.id.text_view_book_number_review)
    lateinit var txtReviewCount1: TextView

    @BindView(R.id.txt_number_reviews)
    lateinit var txtReviewCount2: TextView

    @BindView(R.id.rating_bar)
    lateinit var rattingBar: RatingBar

    @BindView(R.id.text_view_book_rate)
    lateinit var txtRate1: TextView

    @BindView(R.id.txt_book_rate)
    lateinit var txtRate2: TextView

    interface BookDetailListener {
        fun sendBook(book: Book?)
    }

    private lateinit var mBookDetailListener: BookDetailListener

    var mBook: Book? = null
    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(
                BookDetailModule(this, this)
            ).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mCartSnackBarLayout.attachDialogInterface(this)
        mToolbar.title = context!!.getString(R.string.label_app_name)
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mBottomNavigation.visibility = View.GONE

        mBook = arguments!!.getParcelable(Constants.BOOK)
        Picasso.get().load(mBook!!.image).resize(
            Resources.getSystem().displayMetrics.widthPixels,
            Resources.getSystem().displayMetrics.widthPixels * 3 / 2
        )
            .centerCrop().into(imgBook)
        title.text = mBook!!.title.toString()
        author.text = mBook!!.author.toString()
        price.text = "$" + mBook!!.price.toString()

        mPresenter.getReviews(mBook!!.id)
        rcvReviews.layoutManager = LinearLayoutManager(context)
        rcvReviews.hasFixedSize()
        rcvReviews.isNestedScrollingEnabled = false
        rcvReviews.adapter = mAdapter
    }

    @OnClick(R.id.button_write_review, R.id.button_add_to_cart, R.id.view_book_detail)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_write_review -> {
                val bundle = Bundle()
                bundle.putParcelable(Constants.BOOK, mBook)
                mActivity.mNavController.navigate(R.id.writeReviewFragment, bundle)
            }
            R.id.button_add_to_cart -> {
                if (mBook!!.amount == 0) {
                    Toast.makeText(context,"The product is out of stock", Toast.LENGTH_SHORT).show()
                } else {
                    mVogoBookLive.initLiveDataBook(LiveDataBook(Utils.generateKeyFromText(mBook!!.title), mBook))
                    mPresenter.saveCart(mBook)
                    mBookDetailListener.sendBook(mBook)
                    mSnackbar.show()
                }
            }
            R.id.view_book_detail -> {
                if (mSnackbar.isShown)
                    mSnackbar.dismiss()
            }
        }
    }

    fun attachDialogInterface(_interface: BookDetailListener) {
        mBookDetailListener = _interface
    }


    override fun navigateToCart(text: String) {
        mActivity.mNavController.navigate(R.id.cartFragment)
        mSnackbar.dismiss()
    }

    override fun dismissSnackbar() {
        mSnackbar.dismiss()
    }

    override fun onPause() {
        super.onPause()
        mSnackbar.dismiss()
    }

    override fun loadReviewsSuccess(reviews: List<Review>) {
        Collections.reverse(reviews)
        mAdapter.setList(ArrayList(reviews))

        if (reviews.isEmpty()) {
            rattingBar.rating = 0F
            txtRate1.text = "0"
            txtRate2.text = "0"
        } else {
            var rating = 0F
            reviews.forEach {
                rating += it.rate!!
            }
            rattingBar.rating = rating/reviews.size
            txtRate1.text = rattingBar.rating.toString()
            txtRate2.text = rattingBar.rating.toString()
        }
        txtReviewCount1.text = reviews.size.toString()
        txtReviewCount2.text = reviews.size.toString()
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        //TODO
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        //TODO
    }

    override fun setDisposable(disposable: Disposable) {
        //ToDO
    }
}