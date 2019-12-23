package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.presenter.HomeNewReleasePresenter
import com.app.vogobook.presenter.HomeTopSellingPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.HomeCommonResponse
import com.app.vogobook.view.ui.activity.MainActivity

class HomeNewReleaseModelImpl(
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager
) :
    HomeNewReleaseModel {

    private var mPresenter: HomeNewReleasePresenter? = null

    override fun attachPresenter(presenter: HomeNewReleasePresenter) {
        mPresenter = presenter
    }

    override fun loadNewReleaseBooksFromLocal() {
        mRoomUIManager.getBooksByUpdated(object : IRoomListener<Book> {
            override fun showListData(books: List<Book>) {
                mPresenter!!.loadNewReleaseBooksSuccess(books)
            }

        })
    }
}

