package com.app.vogobook.presenter

import com.google.gson.JsonObject

interface AccountPresenter : BasePresenter {
    fun editAccount(jsonObject: JsonObject)
}