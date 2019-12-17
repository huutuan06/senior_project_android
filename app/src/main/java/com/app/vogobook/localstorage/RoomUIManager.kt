package com.app.vogobook.localstorage

import android.os.AsyncTask
import com.app.vogobook.localstorage.dao.*
import com.app.vogobook.localstorage.entities.*
import com.app.vogobook.utils.objects.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomUIManager(
    private var mBookDAO: BookDAO,
    private var mCategoryDAO: CategoryDAO,
    private var mUserDAO: UserDAO,
    private var mOrderDAO: OrderDAO,
    private var mReviewDAO: ReviewDAO,
    private val mCartDAO: CartDAO
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

    fun getAllCarts(userID: Int?, _interface: IRoomListener<Cart>) {
        AsyncTask.execute {
            mCartDAO.getCartsByUserID(userID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Cart>? ->
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

    fun getOrdersByUser(userId: Int?,_interface: IRoomListener<Order>) {
        AsyncTask.execute {
            mOrderDAO.getOrdersByUser(userId).subscribeOn(Schedulers.io())
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
    fun getReviewsByBook(bookId: Int?,_interface: IRoomListener<Review>) {
        AsyncTask.execute {
            mReviewDAO.getReviewByBook(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Review>? ->
                    if (t != null) {
                        _interface.showListData(t)
                    }
                }
        }
    }

    fun saveCart(book: Book?, user_id : Int?) {
        AsyncTask.execute {
            var cart = Cart()
            cart.book_id = book!!.id
            cart.book_title = book.title
            cart.image = book.image
            cart.price = book.price
            cart.user_id = user_id
            cart.total_book = 1
            mCartDAO.getCartsByBookID(book.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { t: List<Cart>? ->
                    if (t != null) {
                        if (t.isNotEmpty()) {
                            cart.id = t[0].id
                            cart.total_book = t[0].total_book!! + 1
                            AsyncTask.execute { mCartDAO.update(cart) }
                        } else {
                            cart.total_book = 1
                            AsyncTask.execute { mCartDAO.saveCart(cart) }
                        }
                    }
                }
        }
    }
}