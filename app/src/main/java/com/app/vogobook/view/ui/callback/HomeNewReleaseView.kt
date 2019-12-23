package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Book

interface HomeNewReleaseView :BaseView {
    fun loadNewReleaseBooksSuccess(books: List<Book>)
}