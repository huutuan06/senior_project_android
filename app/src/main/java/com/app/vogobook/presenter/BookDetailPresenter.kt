package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Review


interface BookDetailPresenter : BasePresenter {
    fun getReviews(bookId: Int?)
    fun loadReviewsSuccess(reviews : List<Review>)
}