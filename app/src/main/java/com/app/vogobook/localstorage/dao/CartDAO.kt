package com.app.vogobook.localstorage.dao

import androidx.room.*
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.localstorage.entities.Order
import com.app.vogobook.localstorage.entities.User
import com.app.vogobook.utils.Constants
import io.reactivex.Maybe

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCarts(cart: List<Cart>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCart(cart: Cart)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(cart: Cart)

    @Delete
    fun delete(cart: Cart)

    @Query("SELECT * FROM " + Constants.DB_TABLE_CART)
    fun getCarts(): Maybe<List<Cart>>

    @Query("SELECT * FROM " + Constants.DB_TABLE_CART + " WHERE " + Constants.TABLE_CART_BOOK_ID + "= :bookID")
    fun getCartsByBookID(bookID: Int?): Maybe<List<Cart>>

    @Query("SELECT * FROM " + Constants.DB_TABLE_CART + " WHERE " + Constants.TABLE_CART_USER_ID + "= :userID")
    fun getCartsByUserID(userID: Int?): Maybe<List<Cart>>

    @Query("UPDATE " + Constants.DB_TABLE_CART + " SET " + Constants.TABLE_CART_TOTAL_BOOK + " =:totalBooks WHERE " + Constants.TABLE_CART_ID + " =:cartId")
    fun updateTotalBookInCart(cartId: Int, totalBooks: Int)
}