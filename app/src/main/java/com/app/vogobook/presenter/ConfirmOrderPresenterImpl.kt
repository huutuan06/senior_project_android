package com.app.vogobook.presenter

import android.content.Context
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.utils.SessionManager
import com.app.vogobook.view.ui.callback.ConfirmOrderView
import com.app.vogobook.viewmodel.ConfirmOrderModel
import io.reactivex.disposables.Disposable


class ConfirmOrderPresenterImpl(
    private val view: ConfirmOrderView,
    private val model: ConfirmOrderModel,
    private val context: Context,
    private val roomUIManager: RoomUIManager,
    private val sessionManager: SessionManager
) : ConfirmOrderPresenter {

    init {
        model.attachPresenter(this)
    }


    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }
}
