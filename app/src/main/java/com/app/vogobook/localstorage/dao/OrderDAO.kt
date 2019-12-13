package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface OrderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOrders(order: List<Order>?)

    @Delete
    fun delete(order: Order)

    @Query("SELECT * FROM " + Constants.DB_TABLE_ORDER )
    fun getOrders() : Maybe<List<Order>>
}