package com.app.vogobook.viewmodel;

import com.app.vogobook.presenter.MainPresenter

interface ConfigureVM {
    fun loadApplicationSettings()
    fun attachPresenter(presenter: MainPresenter)
}
