package com.app.vogobook.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.vogobook.localstorage.dao.BookDAO
import com.app.vogobook.localstorage.dao.CategoryDAO
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.Constants

@Database(entities = [Book::class, Category::class], version = Constants.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DB_NAME = "VogoDB"
    }
    abstract fun getBookDAO() : BookDAO
    abstract fun getCategoryDAO() : CategoryDAO
}