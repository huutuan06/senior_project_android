package com.app.vogobook.presenter

import com.app.vogobook.view.ui.callback.LoginView
import com.app.vogobook.viewmodel.UserViewModel
import com.google.gson.JsonObject

class LoginPresenterImpl(private val view: LoginView, private val userViewModel: UserViewModel) : LoginPresenter {

    init {
        userViewModel.attachPresenter(this)
    }

    override fun loadUser(fullName: String?, email: String?) {
        view.loadUser(fullName, email)
    }

    override fun loginSocial(jsonObject: JsonObject) {
        userViewModel.loginSocial(jsonObject)
    }

    override fun loginFailure() {
        //TODO
    }
}
