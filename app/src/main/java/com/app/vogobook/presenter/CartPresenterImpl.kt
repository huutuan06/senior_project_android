package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.R
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.objects.Utils
import com.app.vogobook.view.ui.callback.CartView
import com.app.vogobook.view.ui.callback.HomeCommonView
import com.app.vogobook.view.ui.callback.HomeTopSellingView
import com.app.vogobook.viewmodel.CartModel
import com.app.vogobook.viewmodel.HomeCommonModel
import com.app.vogobook.viewmodel.HomeTopSellingModel
import io.reactivex.disposables.Disposable


class CartPresenterImpl(
    private val view: CartView,
    private val model: CartModel,
    private val context: Context
) : CartPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun deleteCart(cart: Cart) {
        if (Utils.isInternetOn(context)) {
            view.updateProgressDialog(true)
            model.deleteCart(cart)
        } else {
            view.showMessageDialog(
                context.getString(R.string.label_error),
                context.getString(R.string.label_no_internet)
            )
        }
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
