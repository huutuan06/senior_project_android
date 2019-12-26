package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.service.response.Address

interface ConfirmOrderPresenter : BasePresenter {
    fun submitOrder(address: Address, listCarts: ArrayList<Cart>)
    fun submitOrderSuccess()
    fun submitOrderFailed()
    fun logoutSuccess()
    fun logOut()
}