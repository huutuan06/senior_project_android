package com.app.vogobook.localstorage

import android.os.AsyncTask
import com.app.vogobook.localstorage.dao.BookDAO
import com.app.vogobook.localstorage.dao.CategoryDAO
import com.app.vogobook.localstorage.dao.OrderDAO
import com.app.vogobook.localstorage.dao.UserDAO
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.localstorage.entities.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomUIManager(
    private var mBookDAO: BookDAO,
    private var mCategoryDAO: CategoryDAO,
    private var mUserDAO: UserDAO,
    private var mOrderDAO: OrderDAO
) {

    fun saveAllBooks(listBooks: List<Book>?) {
        AsyncTask.execute {
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
            mCategoryDAO.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Category>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }

    fun getAllBooks(_interface: IRoomListener<Book>) {
        AsyncTask.execute {
            mBookDAO.getBooks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Book>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }

    fun getBooksByCategory(category_id: Int?, _interface: IRoomListener<Book>) {
        AsyncTask.execute {
            mBookDAO.getBooksByCategory(category_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Book>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }

    fun saveUser(user: User?) {
        AsyncTask.execute {
            mUserDAO.saveUser(user)
        }
    }

    fun getUser(_interface: IRoomListener<User>) {
        AsyncTask.execute {
            mUserDAO.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<User>? ->
                if (t != null) {
                    _interface.showListData(t)
                }
            }
        }
    }

    fun saveOrders(listOrders: List<Order>?) {
        AsyncTask.execute {
            mOrderDAO.saveOrders(listOrders)
        }
    }

    fun getOrders(_interface: IRoomListener<Order>) {
        AsyncTask.execute {
            mOrderDAO.getOrders().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Order>? ->
                if (t != null)
                    _interface.showListData(t)
            }
        }
    }

}