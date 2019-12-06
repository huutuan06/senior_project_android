package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.User
import com.google.gson.JsonObject

interface LoginPresenter : BasePresenter {
    fun loginSocial(jsonObject: JsonObject)
    fun loginFailure()
    fun loadUserSuccess(user: User)
}