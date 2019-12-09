package com.app.vogobook.utils

class Constants {

    companion object {

        const val PERMISSION_CODE = 100
        const val IMAGE_PICK_CODE = 101

        const val EMAIL = "email"
        const val PUBLIC_PROFILE = "public_profile"
        const val RC_SIGN_IN = 1
        const val EMPTY_STRING = ""

        const val FIELDS = "fields"
        const val FACEBOOK_PARAMS = "id, name, first_name, last_name, email"

        const val FACEBOOK = "facebook"
        const val GOOGLE = "google"

        const val TIME_DELAY = 1000.toLong()

        const val DB_VERSION = 1

        const val DB_TABLE_BOOK = "books"
        const val TABLE_BOOK_ID = "id"
        const val TABLE_BOOK_TITLE = "title"
        const val TABLE_BOOK_IMAGE = "image"
        const val TABLE_BOOK_CATEGORY_ID = "category_id"
        const val TABLE_BOOK_DESCRIPTION = "description"
        const val TABLE_BOOK_PRICE = "price"
        const val TABLE_BOOK_AMOUNT = "amount"
        const val TABLE_BOOK_AUTHOR = "author"
        const val TABLE_BOOK_CREATED_AT = "created_at"
        const val TABLE_BOOK_UPDATED_AT = "updated_at"

        const val DB_TABLE_CATEGORY = "categories"
        const val TABLE_CATEGORY_ID = "id"
        const val TABLE_CATEGORY_NAME = "name"
        const val TABLE_CATEGORY_DESCRIPTION = "description"
        const val TABLE_CATEGORY_TOTAL_BOOKS = "total_books"
        const val TABLE_CATEGORY_CREATED_AT = "created_at"
        const val TABLE_CATEGORY_UPDATED_UP = "updated_at"
        const val TABLE_CATEGORY_BOOKS = "books"

        const val DB_TABLE_USER = "users"
        const val TABLE_USER_ID = "id"
        const val TABLE_USER_NAME = "name"
        const val TABLE_USER_EMAIL = "email"
        const val TABLE_USER_PASSWORD = "password"
        const val TABLE_USER_PHONE_NUMBER = "phone_number"
        const val TABLE_USER_BIRTH_DAY = "date_of_birth"
        const val TABLE_USER_GENDER = "gender"
        const val TABLE_USER_AVATAR = "avatar"
        const val TABLE_USER_ADDRESS = "address"
        const val TABLE_USER_IS_VERIFIED = "is_verified"
        const val TABLE_USER_TYPE = "type"
        const val TABLE_USER_PLATFORM = "platform"
        const val TABLE_USER_REMEMBER_TOKEN = "remember_token"
        const val TABLE_USER_CREATED_AT = "created_at"
        const val TABLE_USER_UPDATED_AT = "updated_at"

        const val BOOK = "book"
        const val CATEGORY = "category"
        const val USER = "user"
    }
}
