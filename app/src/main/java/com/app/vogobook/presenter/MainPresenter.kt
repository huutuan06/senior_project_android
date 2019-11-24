package com.app.vogobook.presenter

import io.reactivex.disposables.Disposable


interface MainPresenter {
    fun setDisposable(disposable: Disposable)
}
