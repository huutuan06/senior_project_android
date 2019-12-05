package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.presenter.BookCollectionPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.BookCollectionResponse
import com.app.vogobook.view.ui.activity.MainActivity

class BookCollectionModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager
) : BookCollectionModel {

    private var mPresenter: BookCollectionPresenter? = null

    override fun attachPresenter(presenter: BookCollectionPresenter) {
        mPresenter = presenter
    }

    override fun getBookCollection(category_id: Int?) {
        mRoomUIManager.getBooksByCategory(category_id, object : IRoomListener<Book> {
            override fun showListData(books: List<Book>) {
                mPresenter!!.loadBookCollectionSuccess(books)
            }
        })

    }

}