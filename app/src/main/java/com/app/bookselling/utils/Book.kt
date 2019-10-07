package com.app.bookselling.utils

class Book {
    lateinit var title: String
    lateinit var image: String
    lateinit var author: String
    lateinit var price: String
    lateinit var page: String
    lateinit var rate: String


    constructor(title: String, image: String, author: String, rate: String, price: String) {
        this.title = title
        this.image = image
        this.author = author
        this.rate = rate
        this.price = price
    }

    constructor(title: String, image: String, price: String) {
        this.title = title
        this.image = image
        this.price = price
    }

}