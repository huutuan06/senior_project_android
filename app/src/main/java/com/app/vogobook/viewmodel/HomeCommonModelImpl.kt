package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.HomeCommonResponse
import com.app.vogobook.view.ui.activity.MainActivity

class HomeCommonModelImpl(
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager
) :
    HomeCommonModel {

    private var mPresenter: HomeCommonPresenter? = null

    override fun attachPresenter(presenter: HomeCommonPresenter) {
        mPresenter = presenter
    }

    /**
     * Login with social
     */
    override fun getCommonBooks(){
        mPresenter!!.setDisposable(disposableManager.getCommonBooks((service.getCommonBooks()), object : IDisposableListener<HomeCommonResponse> {
            override fun onComplete() {
            }
            override fun onHandleData(response: HomeCommonResponse?) {
                if (response!!.error!!.code == 0) {
                    mActivity.runOnUiThread {
                        ProcessDatabase().execute(response)
                    }
                } else {
                    loadCommonBooksFromLocal()
                }
            }


            override fun onRequestWrongData(code: Int) {
                loadCommonBooksFromLocal()
            }

            override fun onApiFailure(error: Throwable?) {
                loadCommonBooksFromLocal()
            }
        }))
    }

    override fun loadCommonBooksFromLocal() {
        mRoomUIManager.getAllCategories(object : IRoomListener<Category> {
            override fun showListData(categories: List<Category>) {
                mRoomUIManager.getAllBooks(object : IRoomListener<Book> {
                    override fun showListData(books: List<Book>) {
                        mPresenter!!.loadCommonBooksSuccess(categories, books)
                    }
                })
            }
        })
    }

    @SuppressLint("StaticFieldLeak")
    inner class ProcessDatabase : AsyncTask<HomeCommonResponse, HomeCommonResponse, Boolean>() {

        override fun doInBackground(vararg response: HomeCommonResponse): Boolean {
            mRoomUIManager.saveAllBooks(response[0].data!!.books)
            mRoomUIManager.saveAllCategories(response[0].data!!.categories)
            mRoomUIManager.getAllCategories(object : IRoomListener<Category> {
                override fun showListData(t: List<Category>) {
                    mRoomUIManager.getAllBooks(object : IRoomListener<Book> {
                        override fun showListData(t: List<Book>) {
                            onPostExecute(true)
                        }
                    })
                }
            })
            return false
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!) {
                mActivity.runOnUiThread { loadCommonBooksFromLocal() }
            }
        }

    }

}

