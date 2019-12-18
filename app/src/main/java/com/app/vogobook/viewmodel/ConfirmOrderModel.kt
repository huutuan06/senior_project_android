package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.ConfirmOrderPresenter

interface ConfirmOrderModel {
    fun attachPresenter(confirmOrderPresenter: ConfirmOrderPresenter)
}