package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Review

interface BookDetailView :BaseView {
    fun loadReviewsSuccess(reviews: List<Review>)
}