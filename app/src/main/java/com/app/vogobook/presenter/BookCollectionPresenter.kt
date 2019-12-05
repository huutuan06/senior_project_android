package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Book

interface BookCollectionPresenter : BasePresenter {
    fun getBookCollection(category_id: Int?)
    fun loadBookCollectionSuccess(books: List<Book>)
}