package com.app.bookselling.model

import android.net.Uri
import javax.inject.Singleton

@Singleton
class User {
    private lateinit var mPictureUrl: Uri
    private lateinit var mNameUser: String
    private lateinit var mEmailUser: String

    constructor(pictureUrl: Uri?, nameUser: String?, emailUser: String?) {}

    constructor(pictureUrl: Uri?, nameUser: String?) {}
}