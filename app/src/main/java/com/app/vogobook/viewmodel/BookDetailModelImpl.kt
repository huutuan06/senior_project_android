package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.presenter.WriteReviewPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Error
import com.app.vogobook.service.response.ReviewsResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.gson.JsonObject

class BookDetailModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) : BookDetailModel {
    private var mBookId: Int? = null
    private var mPresenter: BookDetailPresenter? = null

    override fun attachPresenter(presenter: BookDetailPresenter) {
        mPresenter = presenter
    }

    override fun getReviews(bookId: Int?) {
        mBookId = bookId
        mPresenter!!.setDisposable(disposableManager.getReviews(service.getReviews(bookId), object : IDisposableListener<ReviewsResponse> {
            override fun onComplete() {
            }

            override fun onHandleData(response: ReviewsResponse?) {
                if (response!!.error!!.code == 0)
                    mActivity.runOnUiThread{
                        ProcessDatabase().execute(response)
                    }
                else
                    loadReviewsFromLocal()
            }

            override fun onRequestWrongData(code: Int) {
                loadReviewsFromLocal()
            }

            override fun onApiFailure(error: Throwable?) {
                loadReviewsFromLocal()
            }

        }))
    }

    override fun loadReviewsFromLocal() {
        mRoomUIManager.getReviewsByBook(mBookId ,object : IRoomListener<Review> {
            override fun showListData(reviews: List<Review>) {
                mPresenter!!.loadReviewsSuccess(reviews)
            }

        })
    }

    @SuppressLint("StaticFieldLeak")
    inner class ProcessDatabase : AsyncTask<ReviewsResponse, ReviewsResponse, Boolean>() {

        override fun doInBackground(vararg response: ReviewsResponse): Boolean {
            mRoomUIManager.saveAllReviews(response[0].data)
            mRoomUIManager.getAllReviews(object : IRoomListener<Review> {
                override fun showListData(t: List<Review>) {
                            onPostExecute(true)
                }
            })
            return false
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!) {
                mActivity.runOnUiThread { loadReviewsFromLocal() }
            }
        }
    }

}