package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.presenter.PersonalPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.OrdersResponse
import com.app.vogobook.service.response.PersonalResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity

class PersonalModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) : PersonalModel {

    private var mPresenter: PersonalPresenter? = null

    override fun attachPresenter(presenter: PersonalPresenter) {
        mPresenter = presenter
    }

    override fun logOut() {
        mPresenter!!.setDisposable(disposableManager.logOut((service.logOut(mSessionManager.token)), object : IDisposableListener<PersonalResponse> {
            override fun onComplete() {
                //TODO
            }

            override fun onHandleData(t: PersonalResponse?) {
                mPresenter!!.logoutSuccess()
            }

            override fun onRequestWrongData(code: Int) {
                mPresenter!!.logoutSuccess()
            }

            override fun onApiFailure(error: Throwable?) {
                mPresenter!!.logoutSuccess()
            }

        }))
    }

    override fun getOrders() {
        mPresenter!!.setDisposable(disposableManager.getOrders(service.getOrder(mSessionManager.token), object : IDisposableListener<OrdersResponse> {
            override fun onComplete() {
                //TODO
            }

            override fun onHandleData(response: OrdersResponse?) {
                if (response!!.error!!.code == 0) {
                    mActivity.runOnUiThread {
                        ProcessDatabase().execute(response)
                    }
                } else {
                    loadOrdersFromLocal()
                }
            }

            override fun onRequestWrongData(code: Int) {
                if (code == 401)
                    mPresenter!!.logOut()
                loadOrdersFromLocal()
            }

            override fun onApiFailure(error: Throwable?) {
                loadOrdersFromLocal()
            }

        }))
    }

    override fun loadOrdersFromLocal() {
        mRoomUIManager.getOrders(object : IRoomListener<Order> {
            override fun showListData(orders: List<Order>) {
                mPresenter!!.getOrdersSuccess(orders)
            }

        })
    }

    @SuppressLint("StaticFieldLeak")
    inner class ProcessDatabase : AsyncTask<OrdersResponse, OrdersResponse, Boolean>() {
        override fun doInBackground(vararg response: OrdersResponse): Boolean {
            mRoomUIManager.saveOrders(response[0].data)
            mRoomUIManager.getOrders(object : IRoomListener<Order>{
                override fun showListData(t: List<Order>) {
                    onPostExecute(true)
                }

            })

            return false
        }

        override fun onPostExecute(result: Boolean?) {
            if (result!!)
                mActivity.runOnUiThread{ loadOrdersFromLocal()}
        }
    }

}