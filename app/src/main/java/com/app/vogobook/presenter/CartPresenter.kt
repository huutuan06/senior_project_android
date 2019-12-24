package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Cart

interface CartPresenter : BasePresenter {
    fun deleteCart(cart: Cart)
    fun updateCart(cartId: Int, totalBooks: Int)
}