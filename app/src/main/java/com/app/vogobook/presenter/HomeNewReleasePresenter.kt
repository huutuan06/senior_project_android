package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Book

interface HomeNewReleasePresenter : BasePresenter {
    fun getNewReleaseBooks()
    fun loadNewReleaseBooksSuccess(books: List<Book>)
}