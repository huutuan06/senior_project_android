package com.app.bookselling.viewmodel;

import com.app.bookselling.presenter.MainPresenter

interface ConfigureVM {
    fun loadApplicationSettings()
    fun attachPresenter(presenter: MainPresenter)
}
