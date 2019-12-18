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
import com.app.vogobook.service.response.ReviewsResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity

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



    fun submitOrder(address: Address, cardList : ArrayList<Cart>) {
        var orderResponseList = OrdersData()
        orderResponseList.user_id = mSessionManager.user_id
        orderResponseList.address = address
        orderResponseList.carts = cardList
    }


}