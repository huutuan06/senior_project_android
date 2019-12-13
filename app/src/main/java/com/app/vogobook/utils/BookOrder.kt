package com.app.vogobook.utils

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookOrder() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("book_id")
    @Expose
    var book_id: Int? = null

    @SerializedName("order_id")
    @Expose
    var order_id: Int? = null

    @SerializedName("total_book")
    @Expose
    var total_book: Int? = null

    @SerializedName("price")
    @Expose
    var price: Float? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        book_id = parcel.readValue(Int::class.java.classLoader) as? Int
        order_id = parcel.readValue(Int::class.java.classLoader) as? Int
        total_book = parcel.readValue(Int::class.java.classLoader) as? Int
        price = parcel.readValue(Float::class.java.classLoader) as? Float
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(book_id)
        parcel.writeValue(order_id)
        parcel.writeValue(total_book)
        parcel.writeValue(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookOrder> {
        override fun createFromParcel(parcel: Parcel): BookOrder {
            return BookOrder(parcel)
        }

        override fun newArray(size: Int): Array<BookOrder?> {
            return arrayOfNulls(size)
        }
    }
}