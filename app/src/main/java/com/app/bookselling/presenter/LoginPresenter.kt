package com.app.bookselling.presenter

import com.google.gson.JsonObject

interface LoginPresenter : BasePresenter {
    fun loginSocial(jsonObject: JsonObject)
    fun loginFailure()
}
