package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Order

interface PersonalPresenter : BasePresenter {
    fun logOut()
    fun logoutSuccess()
    fun getOrders()
    fun getOrdersSuccess(orders: List<Order>)
}