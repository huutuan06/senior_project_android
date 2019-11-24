package com.app.vogobook.view.ui.callback

interface BaseView {
    fun updateProgressDialog(isShowProgressDialog: Boolean)
    fun showErrorMessageDialog(
        errorTitle: String?,
        errorMessage: String?
    )
}