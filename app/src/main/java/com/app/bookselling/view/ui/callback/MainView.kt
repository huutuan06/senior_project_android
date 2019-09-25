package com.app.bookselling.view.ui.callback

import io.reactivex.disposables.Disposable

interface MainView {
    fun setDisposable(disposable: Disposable)
}