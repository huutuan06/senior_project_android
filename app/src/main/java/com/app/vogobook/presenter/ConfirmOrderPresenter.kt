package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.service.response.Address
import kotlin.collections.ArrayList as ArrayList1

interface ConfirmOrderPresenter : BasePresenter {
    fun submitOrder(address: Address, list: ArrayList1<Cart>)
}