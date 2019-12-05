package com.app.vogobook.view.ui.callback

import com.app.vogobook.localstorage.entities.Book

interface BookCollectionView : BaseView {
    fun loadBookCollectionSuccess(books: List<Book>)
}