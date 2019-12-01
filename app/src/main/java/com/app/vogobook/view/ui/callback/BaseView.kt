package com.app.vogobook.view.ui.callback

import io.reactivex.disposables.Disposable

interface BaseView {
    fun updateProgressDialog(isShowProgressDialog: Boolean)
    fun showErrorMessageDialog(
        errorTitle: String?,
        errorMessage: String?
    )
    fun setDisposable(disposable: Disposable)
}