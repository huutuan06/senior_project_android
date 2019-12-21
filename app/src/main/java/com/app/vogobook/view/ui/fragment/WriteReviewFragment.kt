package com.app.vogobook.view.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.navigation.NavController
import butterknife.BindView
import butterknife.OnClick
import com.app.vogobook.R
import com.app.vogobook.analytics.VogoAnalytics
import com.app.vogobook.app.Application
import com.app.vogobook.di.module.MainModule
import com.app.vogobook.di.module.WriteReviewModule
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.presenter.WriteReviewPresenter
import com.app.vogobook.utils.Constants
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.custom.VogoLoadingDialog
import com.app.vogobook.view.ui.activity.MainActivity
import com.app.vogobook.view.ui.callback.WriteReviewView
import com.app.vogobook.view.ui.dialog.VogoDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class WriteReviewFragment : BaseFragment(), WriteReviewView, VogoDialog.IListener {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mActivity: MainActivity

    @Inject
    lateinit var mNavController: NavController

    @Inject
    lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    @Inject
    lateinit var mBottomNavigation: BottomNavigationView

    @Inject
    lateinit var mPresenter: WriteReviewPresenter

    @Inject
    lateinit var mPgDialog: VogoLoadingDialog

    @Inject
    lateinit var mVogoDialog: VogoDialog

    @Inject
    lateinit var mFirebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var mSessionManager: SessionManager

    @Inject
    lateinit var mVogoAnalytics: VogoAnalytics

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
    private var mDisposable:Disposable? = null

    override fun provideYourFragmentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_write_review, container, false)
    }

    override fun distributedDaggerComponents() {
        Application.instance.getAppComponent()!!.plus(MainModule(this.activity as MainActivity))
            .plus(WriteReviewModule(this, this)).inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mBottomNavigation.setOnNavigationItemSelectedListener(mActivity)
        mToolbar.setNavigationOnClickListener { mActivity.onSupportNavigateUp() }
        mActivity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mActivity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        mToolbar.title = "Write Review"
        mBottomNavigation.visibility = View.GONE
        mVogoDialog.setListener(this)
        book = arguments!!.getParcelable(Constants.BOOK)
        Picasso.get().load(book!!.image).resize(Resources.getSystem().displayMetrics.widthPixels ,  Resources.getSystem().displayMetrics.widthPixels*3/2)
            .centerCrop().into(imgBook)
        title.text = book!!.title.toString()
        author.text = book!!.author.toString()
        price.text = "$" + book!!.price.toString()
    }

    @OnClick(R.id.rating_bar, R.id.button_submit)
    fun processEventClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {
                val jsonObject = JsonObject()
                jsonObject.addProperty("book_id",book!!.id)
                jsonObject.addProperty("rate",ratingBar.rating.toString())
                jsonObject.addProperty("review",editTextReview.text.toString())
                jsonObject.addProperty("date", System.currentTimeMillis()/1000)
                updateProgressDialog(true)
                mVogoAnalytics.reportReview(mFirebaseAnalytics, mSessionManager.user_id , editTextReview.text.toString())
                mPresenter.postReview(jsonObject)
            }
        }
    }

    override fun updateProgressDialog(isShowProgressDialog: Boolean) {
        if (isShowProgressDialog) {
            if (!mPgDialog.isShowing) {
                mPgDialog.show()
            }
        } else {
            if (!mActivity.isDestroyed && mPgDialog.isShowing)
                mPgDialog.dismiss()
        }
    }

    override fun showMessageDialog(errorTitle: String?, errorMessage: String?) {
        if (!mVogoDialog.isAdded && !mActivity.isDestroyed) {
            mVogoDialog.updateMessageDialog(mContext, errorTitle, errorMessage)
            mVogoDialog.show(mActivity.supportFragmentManager, "WriteReviewFragment")
        }
    }

    override fun setDisposable(disposable: Disposable) {
        mDisposable = disposable
    }

    override fun onDestroyView() {
        if  (mDisposable != null && mDisposable!!.isDisposed) mDisposable!!.dispose()
        super.onDestroyView()
    }

    override fun doYourAction() {
        mActivity.onBackPressed()
    }

    override fun dimiss() {
        // TODO
    }
}