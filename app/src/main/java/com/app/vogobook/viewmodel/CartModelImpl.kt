package com.app.vogobook.viewmodel

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.CartPresenter
import com.app.vogobook.service.connect.rx.DisposableManager
import com.app.vogobook.service.repository.BookService
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.activity.MainActivity

class CartModelImpl(
    private val context: Context,
    private val service: BookService,
    private val disposableManager: DisposableManager,
    private var mActivity: MainActivity,
    private val mRoomUIManager: RoomUIManager,
    private val mSessionManager: SessionManager
) :
    CartModel {

    private var mPresenter: CartPresenter? = null

    override fun attachPresenter(presenter: CartPresenter) {
        mPresenter = presenter
    }

    override fun deleteCart(cart: Cart) {
        mRoomUIManager.deleteCart(cart)
    }
}

