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
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.BookDetailModule
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.view.custom.CartSnackBarLayout
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.BookDetailView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
import javax.inject.Inject

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
                mBookDetailListener.sendBook(mBook)
                mSnackbar.show()
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

    override fun loadReviewsSuccess(reviews: List<Review>) {
        Log.i("log", reviews.toString())
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