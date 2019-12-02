package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.Category
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface CategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg category: Category?)

    @Update
    fun update(vararg category: Category)

    @Delete
    fun delete(vararg category: Category)

    @Query("SELECT * FROM " + Constants.DB_TABLE_CATEGORY)
    fun getCategories() : Maybe<List<Category>>
}