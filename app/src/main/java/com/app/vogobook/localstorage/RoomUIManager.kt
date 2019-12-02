package com.app.vogobook.localstorage

import android.os.AsyncTask
import com.app.vogobook.localstorage.dao.BookDAO
import com.app.vogobook.localstorage.dao.CategoryDAO
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category

class RoomUIManager(private var mBookDAO: BookDAO,  private var mCategoryDAO: CategoryDAO) {

    fun saveAllBooks(listBooks: List<Book>?) {
        AsyncTask.execute(Runnable {
            mBookDAO.saveAll(*listBooks)
        })
    }

    fun saveAllCategories(listCategories: List<Category>?) {
        AsyncTask.execute(Runnable {
            mCategoryDAO.saveAll(*listCategories)
        })
    }
}