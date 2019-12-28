package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.OrderDetailPresenter

interface OrderDetailModel {
    fun attachPresenter(orderDetailPresenter: OrderDetailPresenter)
    fun cancelOrder(orderId: Int?)
}
