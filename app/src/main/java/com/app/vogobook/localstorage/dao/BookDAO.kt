package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.Book
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg book: List<Book>?)

    @Update
    fun update(vararg book: Book)

    @Delete
    fun delete(vararg book: Book)

    @Query("SELECT * FROM " + Constants.DB_TABLE_BOOK)
    fun getBooks() : Maybe<List<Book>>
}