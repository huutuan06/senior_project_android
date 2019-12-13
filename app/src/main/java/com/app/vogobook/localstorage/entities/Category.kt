package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.app.vogobook.localstorage.typeconverter.BookTypeConverter
import com.app.vogobook.utils.Constants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_CATEGORY)
class Category() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_CATEGORY_ID)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = Constants.TABLE_CATEGORY_NAME)
    @SerializedName("name")
    @Expose
    var name: String? = null

    @ColumnInfo(name = Constants.TABLE_CATEGORY_DESCRIPTION)
    @SerializedName("description")
    @Expose
    var description: String? = null

    @ColumnInfo(name = Constants.TABLE_CATEGORY_TOTAL_BOOKS)
    @SerializedName("total_books")
    @Expose
    var total_books: String? = null

    @ColumnInfo(name = Constants.TABLE_CATEGORY_CREATED_AT)
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @ColumnInfo(name = Constants.TABLE_CATEGORY_UPDATED_AT)
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    @TypeConverters(BookTypeConverter::class)
    @ColumnInfo(name = Constants.TABLE_CATEGORY_BOOKS)
    var arrBooks : List<Book>? = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        description = parcel.readString()
        total_books = parcel.readString()
        created_at = parcel.readString()
        updated_at = parcel.readString()
        arrBooks = parcel.createTypedArrayList(Book)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(total_books)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeTypedList(arrBooks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }

}
