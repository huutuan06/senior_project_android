package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookCollectionData {
    @SerializedName("category")
    @Expose
    var categories: Category? = null

    @SerializedName("books")
    @Expose
    var books: List<Book> ?= null

}
