package com.android.project.presenter

import com.android.project.service.model.Config
import com.android.project.view.ui.callback.MainView
import com.android.project.viewmodel.ConfigureVM
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
