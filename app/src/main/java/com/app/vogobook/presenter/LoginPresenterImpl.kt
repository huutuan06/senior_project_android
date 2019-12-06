package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.view.ui.callback.LoginView
import com.app.vogobook.viewmodel.LoginModel
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

class LoginPresenterImpl(private val view: LoginView, private val loginModel: LoginModel) : LoginPresenter {

    init {
        loginModel.attachPresenter(this)
    }

    override fun loadUserSuccess(user: User) {
        view.loadUserSuccess(user)
    }

    override fun setDisposable(disposable: Disposable) {
        //TODO
    }

    override fun loginSocial(jsonObject: JsonObject) {
        loginModel.loginSocial(jsonObject)
    }

    override fun loginFailure() {
        //TODO
    }
}
