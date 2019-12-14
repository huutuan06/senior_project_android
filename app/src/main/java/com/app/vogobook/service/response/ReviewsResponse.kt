package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Review
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewsResponse {

    @SerializedName("error")
    @Expose
    val error: Error? = null

    @SerializedName("data")
    @Expose
    var data: List<Review> ?= null

}
