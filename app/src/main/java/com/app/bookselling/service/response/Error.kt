package com.app.bookselling.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Error {
    @SerializedName("code")
    @Expose
    var code: Int = 0
        private set
    @SerializedName("message")
    @Expose
    var message: String? = null

    fun setCode(code: Int?) {
        this.code = code!!
    }

}
