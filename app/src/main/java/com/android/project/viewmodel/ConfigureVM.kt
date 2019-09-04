package com.android.project.viewmodel;

import com.android.project.presenter.MainPresenter

interface ConfigureVM {
    fun loadApplicationSettings()
    fun attachPresenter(presenter: MainPresenter)
}
