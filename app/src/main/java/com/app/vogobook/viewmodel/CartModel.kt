package com.app.vogobook.viewmodel

import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.CartPresenter

interface CartModel {
    fun attachPresenter(cartPresenter: CartPresenter)
    fun deleteCart(cart: Cart)
}
