package com.app.vogobook.presenter

import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category

interface HomeCommonPresenter : BasePresenter {
    fun getCommonBooks()
    fun loadCommonBooksSuccess(
        categories: List<Category>,
        books: List<Book>
    )
}