package com.app.vogobook.presenter

import com.google.gson.JsonObject

interface WriteReviewPresenter : BasePresenter {
    fun postReview(jsonObject: JsonObject)
    fun submitReviewSuccess()
    fun submitReviewFailed()
}