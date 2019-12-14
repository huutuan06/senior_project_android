package com.app.vogobook.localstorage

import android.os.AsyncTask
import com.app.vogobook.localstorage.dao.*
import com.app.vogobook.localstorage.entities.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomUIManager(
    private var mBookDAO: BookDAO,
    private var mCategoryDAO: CategoryDAO,
    private var mUserDAO: UserDAO,
    private var mOrderDAO: OrderDAO,
    private var mReviewDAO: ReviewDAO
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

    fun saveAllReviews(listReviews: List<Review>?) {
        AsyncTask.execute {
            mReviewDAO.saveAll(listReviews)
        }
    }

    fun getAllReviews(_interface: IRoomListener<Review>) {
        AsyncTask.execute {
            mReviewDAO.getReviews().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Review>? ->
                    if (t != null) {
                        _interface.showListData(t)
                    }
                }
        }
    }

}