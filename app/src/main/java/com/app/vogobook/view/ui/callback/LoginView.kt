package com.app.vogobook.view.ui.callback

interface LoginView : BaseView {
    fun loadUser(fullName: String?, email: String?)
}