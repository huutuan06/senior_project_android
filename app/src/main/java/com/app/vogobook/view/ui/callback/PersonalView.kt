package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Order

interface PersonalView :BaseView {
    fun logoutSuccess()
    fun getOrdersSuccess(orders: List<Order>)
}