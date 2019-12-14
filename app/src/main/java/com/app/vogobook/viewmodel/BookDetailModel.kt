package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.BookDetailPresenter
import com.app.vogobook.presenter.WriteReviewPresenter
import com.google.gson.JsonObject

interface BookDetailModel {
    fun attachPresenter(bookDetailPresenter: BookDetailPresenter)
}