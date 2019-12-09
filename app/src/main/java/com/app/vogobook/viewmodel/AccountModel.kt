package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.AccountPresenter
import com.google.gson.JsonObject

interface AccountModel {
    fun attachPresenter(accountPresenter: AccountPresenter)
    fun editAccount(jsonObject: JsonObject)
}