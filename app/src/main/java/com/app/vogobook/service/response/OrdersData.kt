package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Order
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrdersData {
    @SerializedName("orders")
    @Expose
    var orders: Order ?= null

}
