package com.app.bookselling.view.ui.callback

interface LoginView : BaseView {
    fun errorConnection()
    fun loadUser(fullName: String?, email: String?)
}