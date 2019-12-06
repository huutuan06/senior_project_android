package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.User

interface LoginView : BaseView {
    fun loadUserSuccess(user: User)
}