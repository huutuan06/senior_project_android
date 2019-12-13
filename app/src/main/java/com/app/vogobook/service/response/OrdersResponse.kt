package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Order
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrdersResponse {

    @SerializedName("message")
    @Expose
    val message: Message? = null

    @SerializedName("data")
    @Expose
    val data: List<Order>? = null

}
