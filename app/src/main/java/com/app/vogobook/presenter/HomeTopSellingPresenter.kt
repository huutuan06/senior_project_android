package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Book

interface HomeTopSellingPresenter : BasePresenter {
    fun getTopSellingBooks()
    fun loadToppSellingBooksSuccess(books: List<Book>)
}