package com.android.project.presenter

import com.android.project.service.model.Config
import io.reactivex.disposables.Disposable

interface MainPresenter {

    fun setDisposable(disposable: Disposable)

    fun loadApplicationSettings()

    fun loadAppConfigurationSuccess(config: Config)

    fun loadAppConfigurationFailure(error: String)

}
