package com.app.bookselling.presenter

import com.app.bookselling.view.ui.callback.LoginView
import com.app.bookselling.viewmodel.UserViewModel
import com.google.gson.JsonObject

class LoginPresenterImpl(private val view: LoginView, private val userViewModel: UserViewModel) : LoginPresenter {

    override fun loadUser(fullName: String?, email: String?) {
        // Using view to get data to Login Screen
        view.loadUser(fullName, email)
    }

    override fun loginFailure() {
        //TODO
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
