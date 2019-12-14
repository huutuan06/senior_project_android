package com.app.vogobook.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.vogobook.localstorage.dao.*
import com.app.vogobook.localstorage.entities.*
import com.app.vogobook.utils.Constants

@Database(entities = [Book::class, Category::class, User::class, Order::class, Review::class, Cart::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DB_NAME = "VogoDB"
    }
    abstract fun getBookDAO() : BookDAO
    abstract fun getCategoryDAO() : CategoryDAO
    abstract fun getUserDAO() : UserDAO
    abstract fun getOrderDAO() : OrderDAO
    abstract fun getReviewDAO() : ReviewDAO
    abstract fun getCartDAO() : CartDAO
}