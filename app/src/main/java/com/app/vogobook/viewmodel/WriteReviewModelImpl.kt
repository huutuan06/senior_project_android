package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.WriteReviewPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Error
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.gson.JsonObject

class WriteReviewModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) : WriteReviewModel {

    private var mPresenter: WriteReviewPresenter? = null

    override fun attachPresenter(presenter: WriteReviewPresenter) {
        mPresenter = presenter
    }

    override fun postReview(jsonObject: JsonObject) {
        mPresenter!!.setDisposable(disposableManager.postReview(service.postReview(mSessionManager.token, jsonObject), object : IDisposableListener<Error> {
            override fun onComplete() {
            }

            override fun onHandleData(t: Error?) {
                if (t!!.code == 0)
                    mPresenter!!.submitReviewSuccess()
                else
                    mPresenter!!.submitReviewFailed()
            }

            override fun onRequestWrongData(code: Int) {
                mPresenter!!.submitReviewFailed()
            }

            override fun onApiFailure(error: Throwable?) {
//                mPresenter!!.logoutSuccess()
            }

        }))
    }


}