package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.LoginPresenter
import com.google.gson.JsonObject

interface UserViewModel {
    fun attachPresenter(loginPresenter: LoginPresenter)
    fun loginSocial(jsonObject: JsonObject)
}
