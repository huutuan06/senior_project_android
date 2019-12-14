package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.BookDetailPresenter

interface BookDetailModel {
    fun attachPresenter(bookDetailPresenter: BookDetailPresenter)
    fun getReviews(bookId: Int?)
    fun loadReviewsFromLocal()
}