package com.app.bookselling.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("error")
    @Expose
    val error: Error? = null

    @SerializedName("data")
    @Expose
    val data: UserData? = null

}
