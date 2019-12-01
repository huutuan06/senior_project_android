package com.app.vogobook.presenter

import io.reactivex.disposables.Disposable

interface BasePresenter {
    fun setDisposable(commonBooks: Disposable)
}