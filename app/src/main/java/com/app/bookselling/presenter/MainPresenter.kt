package com.app.bookselling.presenter

import io.reactivex.disposables.Disposable


interface MainPresenter {
    fun setDisposable(disposable: Disposable)
}
