package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.PersonalView
import com.app.vogobook.viewmodel.PersonalModel
import io.reactivex.disposables.Disposable


class PersonalPresenterImpl(
    private val view: PersonalView,
    private val model: PersonalModel,
    private val context: Context
) : PersonalPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun logOut() {
        if (Utils.isInternetOn(context)) {
            model.logOut()
        } else {
            view.showErrorMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun logoutSuccess() {
        view.logoutSuccess()
    }

    override fun getOrders() {
        view.updateProgressDialog(true)
        model.getOrders()
    }

    override fun getOrdersSuccess(orders: List<Order>) {
        view.updateProgressDialog(false)
        view.getOrdersSuccess(orders)
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
