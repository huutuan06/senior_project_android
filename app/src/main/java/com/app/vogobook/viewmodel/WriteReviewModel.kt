package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.WriteReviewPresenter
import com.google.gson.JsonObject

interface WriteReviewModel {
    fun attachPresenter(writeReviewPresenter: WriteReviewPresenter)
    fun postReview(jsonObject: JsonObject)
}