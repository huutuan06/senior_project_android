package com.app.vogobook.service.response

import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeCommonData {
    @SerializedName("categories")
    @Expose
    var categories: List<Category> ?= null

    @SerializedName("books")
    @Expose
    var books: List<Book> ?= null

}
