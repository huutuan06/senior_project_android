package com.app.vogobook.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonalResponse {

    @SerializedName("error")
    @Expose
    val error: Error? = null

    @SerializedName("data")
    @Expose
    val data: String? = null

}
