package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.HomeTopSellingPresenter

interface HomeTopSellingModel {
    fun attachPresenter(homeTopSellingPresenter: HomeTopSellingPresenter)
    fun loadTopSellingBooksFromLocal()
}
