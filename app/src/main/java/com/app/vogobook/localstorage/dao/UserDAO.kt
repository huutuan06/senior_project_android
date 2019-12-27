package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User?)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM " + Constants.DB_TABLE_USER )
    fun getUser() : Maybe<List<User>>

    @Query("DELETE FROM " + Constants.DB_TABLE_USER)
    fun deleteAllUsers()
}