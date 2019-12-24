package com.app.vogobook.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import com.app.vogobook.localstorage.IRoomListener
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Address
import com.app.vogobook.service.model.OrdersData
import com.app.vogobook.service.response.Error
import com.app.vogobook.service.response.ReviewsResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity
import com.google.gson.Gson
import com.google.gson.JsonObject

class ConfirmOrderModelImpl (
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) : ConfirmOrderModel {
    private var mBookId: Int? = null
    private var mPresenter: ConfirmOrderPresenter? = null

    override fun attachPresenter(presenter: ConfirmOrderPresenter) {
        mPresenter = presenter
    }

    override fun submitOrder(address: Address, listCarts : ArrayList<Cart>) {
        val orderResponseList = OrdersData()
        orderResponseList.user_id = mSessionManager.user_id
        orderResponseList.address = address
        orderResponseList.carts = listCarts
//        val jsonObject = JsonObject()
//        jsonObject.addProperty("Order",Gson().toJson(orderResponseList))
        mPresenter!!.setDisposable(disposableManager.submitOrder(service.submitOrder(mSessionManager.token, orderResponseList), object : IDisposableListener<Error> {
            override fun onComplete() {
            }

            override fun onHandleData(t: Error?) {
                if (t!!.code == 0)
                    mPresenter!!.submitOrderSuccess()
                else
                    mPresenter!!.submitOrderFailed()
            }

            override fun onRequestWrongData(code: Int) {
                mPresenter!!.submitOrderFailed()
            }

            override fun onApiFailure(error: Throwable?) {
            }
        }))
    }
}