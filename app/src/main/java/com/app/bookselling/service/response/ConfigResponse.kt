package com.app.bookselling.service.response

import com.app.bookselling.service.model.Config
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConfigResponse {

    @SerializedName("error")
    @Expose
    val error: Error? = null
    @SerializedName("data")
    @Expose
    val data: Config? = null

}
