package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.HomeCommonPresenter

interface HomeCommonModel {
    fun attachPresenter(homeCommonPresenter: HomeCommonPresenter)
    fun loadCommonBooksFromLocal()
    fun getCommonBooks()
}
