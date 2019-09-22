package com.app.bookselling.presenter

import com.app.bookselling.view.ui.callback.LoginView
import com.app.bookselling.viewmodel.UserViewModel
import com.google.gson.JsonObject

class LoginPresenterImpl(private val view: LoginView, private val userViewModel: UserViewModel) : LoginPresenter {
    override fun loginFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        userViewModel.attachPresenter(this)
    }

    override fun loginSocial(jsonObject: JsonObject) {
//        view.showDialogProgress()
        userViewModel.loginSocial(jsonObject)
    }

    // Common Error
    override fun errorConnection() {
//        view.closeDialogProgress()
        view.errorConnection()
    }

}
