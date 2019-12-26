package com.app.vogobook.viewmodel

import com.app.vogobook.presenter.HomeNewReleasePresenter

interface `HomeNewReleaseModel` {
    fun attachPresenter(homeNewReleasePresenter: HomeNewReleasePresenter)
    fun loadNewReleaseBooksFromLocal()
}
