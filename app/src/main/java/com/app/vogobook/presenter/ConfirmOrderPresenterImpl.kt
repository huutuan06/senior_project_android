package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.service.response.Address
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.viewmodel.ConfirmOrderModel
import io.reactivex.disposables.Disposable


class ConfirmOrderPresenterImpl(
    private val view: ConfirmOrderView,
    private val model: ConfirmOrderModel,
    private val context: Context,
    private val roomUIManager: RoomUIManager,
    private val sessionManager: SessionManager
) : ConfirmOrderPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun submitOrder(address: Address, listCarts: ArrayList<Cart>) {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.submitOrder(address, listCarts)
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun logOut() {
        if (Utils.isInternetOn(context)) {
            model.logOut()
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun submitOrderSuccess() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_success), context.getString(R.string.submit_order_successfully))
    }

    override fun submitOrderFailed() {
        view.updateProgressDialog(false)
        view.showMessageDialog(context.getString(R.string.label_failure), context.getString(R.string.cannot_process_request))
    }

    override fun logoutSuccess() {
        view.logoutSuccess()
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
