package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Order
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrdersResponse {

    @SerializedName("error")
    @Expose
    val error: Error? = null

    @SerializedName("data")
    @Expose
    val data: List<Order>? = null

}
