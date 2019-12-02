package com.app.vogobook.localstorage

import android.os.AsyncTask
import com.app.vogobook.localstorage.dao.BookDAO
import com.app.vogobook.localstorage.dao.CategoryDAO
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomUIManager(private var mBookDAO: BookDAO,  private var mCategoryDAO: CategoryDAO) {

    fun saveAllBooks(listBooks: List<Book>?) {
        AsyncTask.execute{
            mBookDAO.saveAll(listBooks)
        }
    }

    fun saveAllCategories(listCategories: List<Category>?) {
        AsyncTask.execute {
            mCategoryDAO.saveAll(listCategories)
        }
    }

    fun getAllCategories(_interface: IRoomListener<Category>) {
        AsyncTask.execute {
            mCategoryDAO.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Category>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }

    fun getAllBooks(_interface: IRoomListener<Book>) {
        AsyncTask.execute {
            mBookDAO.getBooks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Book>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }
}