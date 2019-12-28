package com.app.vogobook.presenter

interface OrderDetailPresenter : BasePresenter {
    fun cancelOrder(orderId: Int?)
    fun cancelOrderSuccess()
    fun cancelOrderFailed()
}