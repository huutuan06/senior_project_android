package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.PersonalPresenter

interface PersonalModel {
    fun attachPresenter(personalPresenter: PersonalPresenter)
    fun logOut()
}