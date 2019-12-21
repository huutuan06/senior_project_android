package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.vogobook.utils.Constants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_CART)
class Cart() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_CART_ID)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = Constants.TABLE_CART_USER_ID)
    @SerializedName("user_id")
    @Expose
    var user_id: Int? = null

    @ColumnInfo(name = Constants.TABLE_CART_BOOK_ID)
    @SerializedName("book_id")
    @Expose
    var book_id: Int? = null

    @ColumnInfo(name = Constants.TABLE_CART_BOOK_TITLE)
    @SerializedName("book_title")
    @Expose
    var book_title: String? = null

    @ColumnInfo(name = Constants.TABLE_CART_TOTAL_BOOK)
    @SerializedName("total_book")
    @Expose
    var total_book: Int? = null

    @ColumnInfo(name = Constants.TABLE_CART_BOOK_AUTHOR)
    @SerializedName("book_author")
    @Expose
    var book_author: String? = null

    @ColumnInfo(name = Constants.TABLE_CART_PRICE)
    @SerializedName("price")
    @Expose
    var price: Float? = null

    @ColumnInfo(name = Constants.TABLE_CART_IMAGE)
    @SerializedName("image")
    @Expose
    var image: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        book_id = parcel.readValue(Int::class.java.classLoader) as? Int
        book_title = parcel.readString()
        total_book = parcel.readValue(Int::class.java.classLoader) as? Int
        book_author = parcel.readString()
        price = parcel.readValue(Float::class.java.classLoader) as? Float
        image = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(user_id)
        parcel.writeValue(book_id)
        parcel.writeString(book_title)
        parcel.writeValue(total_book)
        parcel.writeString(book_author)
        parcel.writeValue(price)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cart> {
        override fun createFromParcel(parcel: Parcel): Cart {
            return Cart(parcel)
        }

        override fun newArray(size: Int): Array<Cart?> {
            return arrayOfNulls(size)
        }
    }

}