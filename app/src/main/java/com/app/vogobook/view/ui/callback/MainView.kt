package com.app.vogobook.view.ui.callback

import io.reactivex.disposables.Disposable

interface MainView {
    fun setDisposable(disposable: Disposable)
}