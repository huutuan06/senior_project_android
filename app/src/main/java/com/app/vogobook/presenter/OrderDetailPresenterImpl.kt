package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.service.response.Address
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.view.ui.callback.OrderDetailView
import com.app.vogobook.viewmodel.ConfirmOrderModel
import com.app.vogobook.viewmodel.OrderDetailModel
import io.reactivex.disposables.Disposable


class OrderDetailPresenterImpl(
    private val view: OrderDetailView,
    private val model: OrderDetailModel,
    private val context: Context,
    private val roomUIManager: RoomUIManager,
    private val sessionManager: SessionManager
) : OrderDetailPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun cancelOrder(orderId: Int?) {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.cancelOrder(orderId)
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun cancelOrderSuccess() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_success), context.getString(R.string.cancel_order_successfully))
    }

    override fun cancelOrderFailed() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_failure), context.getString(R.string.cannot_process_request))
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }

}
