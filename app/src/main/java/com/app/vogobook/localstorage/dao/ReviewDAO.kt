package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.localstorage.entities.Review
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface ReviewDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(review: List<Review>?)

    @Update
    fun update(review: Review)

    @Delete
    fun delete(review: Review)

    @Query("SELECT * FROM " + Constants.DB_TABLE_REVIEW)
    fun getReviews() : Maybe<List<Review>>

    @Query("SELECT * FROM " + Constants.DB_TABLE_REVIEW + " WHERE " + Constants.TABLE_REVIEW_BOOK_ID + "= :bookId")
    fun getReviewByBook(bookId: Int?) : Maybe<List<Review>>
}