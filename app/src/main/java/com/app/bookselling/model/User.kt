package com.app.bookselling.model

class User {
    private lateinit var mPictureUrl: String
    private lateinit var mNameUser: String
    private lateinit var mEmailUser: String

    constructor(pictureUrl: String, nameUser: String, emailUser: String) {}

    constructor(pictureUrl: String, nameUser: String) {}
}