package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.app.vogobook.localstorage.typeconverter.BookOrderTypeConverter
import com.app.vogobook.utils.BookOrder
import com.app.vogobook.utils.Constants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_REVIEW)
class Review() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_REVIEW_ID)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_USER_ID)
    @SerializedName("user_id")
    @Expose
    var user_id: Int? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_USER_NAME)
    @SerializedName("user_name")
    @Expose
    var user_name: String? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_USER_AVATAR)
    @SerializedName("user_avatar")
    @Expose
    var user_avatar: String? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_BOOK_ID)
    @SerializedName("book_id")
    @Expose
    var book_id: Int? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_RATE)
    @SerializedName("rate")
    @Expose
    var rate: Float? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_CONTENT)
    @SerializedName("content")
    @Expose
    var content: String? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_DATE)
    @SerializedName("date")
    @Expose
    var date: String? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_CREATED_AT)
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @ColumnInfo(name = Constants.TABLE_REVIEW_UPDATED_AT)
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        user_name = parcel.readString()
        user_avatar = parcel.readString()
        book_id = parcel.readValue(Int::class.java.classLoader) as? Int
        rate = parcel.readValue(Float::class.java.classLoader) as? Float
        content = parcel.readString()
        date = parcel.readString()
        created_at = parcel.readString()
        updated_at = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(user_id)
        parcel.writeString(user_name)
        parcel.writeString(user_avatar)
        parcel.writeValue(book_id)
        parcel.writeValue(rate)
        parcel.writeString(content)
        parcel.writeString(date)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }

}
