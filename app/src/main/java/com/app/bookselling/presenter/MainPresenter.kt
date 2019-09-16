package com.app.bookselling.presenter

import com.app.bookselling.service.model.Config
import io.reactivex.disposables.Disposable


interface MainPresenter {

    fun setDisposable(disposable: Disposable)

    fun loadApplicationSettings()

    fun loadAppConfigurationSuccess(config: Config)

    fun loadAppConfigurationFailure(error: String)

}
