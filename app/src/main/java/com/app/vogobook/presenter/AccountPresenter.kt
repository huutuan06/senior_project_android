package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.localstorage.entities.User
import com.google.gson.JsonObject

interface AccountPresenter : BasePresenter {
    fun editAccount(jsonObject: JsonObject)
}