package com.app.bookselling.utils

class Book {
    lateinit var title: String
    lateinit var image: String
    lateinit var author: String
    lateinit var price: String
    lateinit var page: String


    constructor(title: String, image: String, author: String, page: String) {
        this.title = title
        this.image = image
        this.author = author
        this.page = page
    }

    constructor(title: String, image: String, price: String) {
        this.title = title
        this.image = image
        this.price = price
    }


}