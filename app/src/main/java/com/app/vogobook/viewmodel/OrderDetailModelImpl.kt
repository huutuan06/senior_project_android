package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.presenter.OrderDetailPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.connect.rx.IDisposableListener
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.service.response.Error
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity

class OrderDetailModelImpl(
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) :
    OrderDetailModel {

    private var mPresenter: OrderDetailPresenter? = null

    override fun attachPresenter(presenter: OrderDetailPresenter) {
        mPresenter = presenter
    }

    override fun cancelOrder(orderId: Int?) {
        mPresenter!!.setDisposable(disposableManager.cancelOrder(service.cancelOrder(mSessionManager.token, orderId), object :
            IDisposableListener<Error> {
            override fun onComplete() {
            }

            override fun onHandleData(t: Error?) {
                if (t!!.code == 0)
                    mPresenter!!.cancelOrderSuccess()
                else
                    mPresenter!!.cancelOrderFailed()
            }

            override fun onRequestWrongData(code: Int) {
                mPresenter!!.cancelOrderFailed()
            }

            override fun onApiFailure(error: Throwable?) {
            }
        }))
    }
}

