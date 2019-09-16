package com.app.bookselling.view.ui.callback

import com.app.bookselling.service.model.Config
import io.reactivex.disposables.Disposable

interface MainView {
    fun setDisposable(disposable: Disposable)
    fun loadAppConfigurationSuccess(config: Config)
    fun loadAppConfigurationFailure(error: String)
}