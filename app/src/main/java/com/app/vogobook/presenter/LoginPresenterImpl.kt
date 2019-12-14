package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.view.ui.callback.LoginView
import com.app.vogobook.viewmodel.LoginModel
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

/**
 * Created by ben on xx/xxx/2019.
 */
class LoginPresenterImpl(private val view: LoginView, private val loginModel: LoginModel) : LoginPresenter {

    init {
        loginModel.attachPresenter(this)
    }

    override fun loadUserSuccess(user: User) {
        view.loadUserSuccess(user)
        view.updateProgressDialog(false)
    }

    override fun setDisposable(disposable: Disposable) {
        view.setDisposable(disposable)
    }

    override fun loginSocial(jsonObject: JsonObject) {
        loginModel.loginSocial(jsonObject)
        view.updateProgressDialog(true)
    }

    override fun loginFailure() {
        //TODO
    }
}
