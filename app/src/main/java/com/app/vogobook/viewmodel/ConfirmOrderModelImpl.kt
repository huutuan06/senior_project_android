package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.model.OrdersData
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Address

import com.app.vogobook.service.response.Error
import com.app.vogobook.service.response.PersonalResponse
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity

class ConfirmOrderModelImpl(
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

    override fun submitOrder(address: Address, listCarts: ArrayList<Cart>) {
        val orderResponseList = OrdersData()
        orderResponseList.user_id = mSessionManager.user_id
        orderResponseList.address = address
        orderResponseList.carts = listCarts
        mPresenter!!.setDisposable(
            disposableManager.submitOrder(
                service.submitOrder(
                    mSessionManager.token,
                    orderResponseList
                ), object : IDisposableListener<Error> {
                    override fun onComplete() {
                    }

                    override fun onHandleData(t: Error?) {
                        if (t!!.code == 0)
                            mPresenter!!.submitOrderSuccess()
                        else if (t.code == 401)
                            mPresenter!!.logOut()
                        else
                            mPresenter!!.submitOrderFailed()
                    }

                    override fun onRequestWrongData(code: Int) {
                        // TODO
                    }

                    override fun onApiFailure(error: Throwable?) {
                        // TODO
                    }
                })
        )
    }

    override fun logOut() {
        mPresenter!!.setDisposable(
            disposableManager.logOut(
                (service.logOut(mSessionManager.token)),
                object : IDisposableListener<PersonalResponse> {
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

                })
        )
    }
}