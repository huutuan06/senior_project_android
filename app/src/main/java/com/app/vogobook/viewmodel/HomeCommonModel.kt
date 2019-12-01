package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.HomeCommonPresenter
import com.app.vogobook.presenter.LoginPresenter
import com.google.gson.JsonObject

interface HomeCommonModel {
    fun attachPresenter(homeCommonPresenter: HomeCommonPresenter)
    fun getCommonBooks()
}
