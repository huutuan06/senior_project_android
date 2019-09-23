package com.app.bookselling.viewmodel

import com.app.bookselling.presenter.LoginPresenter
import com.google.gson.JsonObject

interface UserViewModel {
    fun attachPresenter(loginPresenter: LoginPresenter)
    fun loginSocial(jsonObject: JsonObject)
}
