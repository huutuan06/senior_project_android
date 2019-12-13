package com.app.vogobook.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

}
