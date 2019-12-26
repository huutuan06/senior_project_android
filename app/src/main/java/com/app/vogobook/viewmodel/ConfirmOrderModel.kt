package com.app.vogobook.viewmodel

import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.presenter.ConfirmOrderPresenter
import com.app.vogobook.service.response.Address

interface ConfirmOrderModel {

    fun submitOrder(address: Address, list: ArrayList<Cart>)

    fun attachPresenter(confirmOrderPresenter: ConfirmOrderPresenter)
    fun submitOrder(address: Address, listCarts: ArrayList<Cart>)
    fun logOut()
}