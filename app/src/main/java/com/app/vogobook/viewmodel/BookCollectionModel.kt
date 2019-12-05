package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.BookCollectionPresenter

interface BookCollectionModel {
    fun attachPresenter(bookCollectionPresenter: BookCollectionPresenter)
    fun getBookCollection(category_id: Int?)
}