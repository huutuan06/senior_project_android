package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.RoomUIManager
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
    public val mRoomUIManager: RoomUIManager
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
                    mActivity.runOnUiThread(Runnable {
                        ProcessDatabase().execute(response)
                    })
                } else {
//                    loadBooksWithoutInternet()
                }
            }


            override fun onRequestWrongData(code: Int) {
//                    mloginPresenter!!.loginFailure()
            }

            override fun onApiFailure(error: Throwable?) {
//                    mloginPresenter!!.loginFailure()
            }
        }))
    }

    @SuppressLint("StaticFieldLeak")
    inner class ProcessDatabase : AsyncTask<HomeCommonResponse, HomeCommonResponse, Boolean>() {
        override fun doInBackground(vararg response: HomeCommonResponse): Boolean {
            mRoomUIManager.saveAllBooks(response[0].data!!.books)
            mRoomUIManager.saveAllCategories(response[0].data!!.categories)
            return false
        }

    }

}

