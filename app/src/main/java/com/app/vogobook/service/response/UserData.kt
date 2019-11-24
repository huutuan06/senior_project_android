package com.app.vogobook.service.response

import com.app.vogobook.service.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserData {
    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("token")
    @Expose
    var token: String? = null

}
