package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.presenter.WriteReviewPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Error
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

    private var mPresenter: BookDetailPresenter? = null

    override fun attachPresenter(presenter: BookDetailPresenter) {
        mPresenter = presenter
    }

}