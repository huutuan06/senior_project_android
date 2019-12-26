package com.app.vogobook.livedata.`object`

import com.app.vogobook.localstorage.entities.Book

/**
 * We had key
 * We had object named Book
 */
class LiveDataBook {
    var key: String? = null
    var book: Book? = null

    constructor(key: String?, book: Book?) {
        this.key = key
        this.book = book
    }
}

