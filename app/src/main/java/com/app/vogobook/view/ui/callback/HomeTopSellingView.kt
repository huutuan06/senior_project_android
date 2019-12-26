package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Book

interface HomeTopSellingView :BaseView {
    fun loadTopSellingBooksSuccess(books: List<Book>)
}