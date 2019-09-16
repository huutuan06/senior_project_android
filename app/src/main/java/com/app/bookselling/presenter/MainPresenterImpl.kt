package com.app.bookselling.presenter

import com.app.bookselling.service.model.Config
import com.app.bookselling.view.ui.callback.MainView
import com.app.bookselling.viewmodel.ConfigureVM
import io.reactivex.disposables.Disposable

class MainPresenterImpl(private val view: MainView, private val model: ConfigureVM) : MainPresenter {

    init {
        model.attachPresenter(this)
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }

    override fun loadApplicationSettings() {
        model.loadApplicationSettings()
    }

    override fun loadAppConfigurationFailure(error: String) {
        view.loadAppConfigurationFailure(error)
    }

    override fun loadAppConfigurationSuccess(config: Config) {
        view.loadAppConfigurationSuccess(config)
    }

}
