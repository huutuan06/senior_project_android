package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.vogobook.utils.Constants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_BOOK)
class Book() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_BOOK_ID)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_TITLE)
    @SerializedName("title")
    @Expose
    var title: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_IMAGE)
    @SerializedName("image")
    @Expose
    var image: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_CATEGORY_ID)
    @SerializedName("category_id")
    @Expose
    var category_id: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_DESCRIPTION)
    @SerializedName("description")
    @Expose
    var description: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_PRICE)
    @SerializedName("price")
    @Expose
    var price: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_AMOUNT)
    @SerializedName("amount")
    @Expose
    var amount: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_AUTHOR)
    @SerializedName("author")
    @Expose
    var author: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_CREATED_AT)
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @ColumnInfo(name = Constants.TABLE_BOOK_UPDATED_AT)
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        image = parcel.readString()
        category_id = parcel.readString()
        description = parcel.readString()
        price = parcel.readString()
        amount = parcel.readString()
        author = parcel.readString()
        created_at = parcel.readString()
        updated_at = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(category_id)
        parcel.writeString(description)
        parcel.writeString(price)
        parcel.writeString(amount)
        parcel.writeString(author)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}