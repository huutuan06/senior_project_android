package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.app.vogobook.localstorage.typeconverter.BookOrderTypeConverter
import com.app.vogobook.service.model.BookOrder
import com.app.vogobook.utils.Constants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_ORDER)
class Order() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_ORDER_ID)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_NAME)
    @SerializedName("name")
    @Expose
    var name: String? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_CODE)
    @SerializedName("code")
    @Expose
    var code: String? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_USER_ID)
    @SerializedName("user_id")
    @Expose
    var user_id: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_ADDRESS)
    @SerializedName("address")
    @Expose
    var address: String? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_PAYMENT)
    @SerializedName("is_payment")
    @Expose
    var payment: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_CONFIRMED_ORDERING)
    @SerializedName("is_confirm_ordering")
    @Expose
    var confirm_ordering: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_UNSUCCESSFUL_PAYMENT)
    @SerializedName("is_unsuccessfull_payment")
    @Expose
    var unsuccessfull_payment: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_DELIVERY)
    @SerializedName("is_delivery")
    @Expose
    var delivery: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_SUCCESS)
    @SerializedName("is_success")
    @Expose
    var success: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_IS_CANCEL)
    @SerializedName("is_cancel")
    @Expose
    var cancel: Int? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_CREATED_AT)
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @ColumnInfo(name = Constants.TABLE_ORDER_UPDATED_AT)
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    @TypeConverters(BookOrderTypeConverter::class)
    @ColumnInfo(name = Constants.TABLE_ORDER_BOOKS)
    @SerializedName("book_order")
    @Expose
    var arrBooks : List<BookOrder>? = ArrayList()

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        code = parcel.readString()
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        address = parcel.readString()
        payment = parcel.readValue(Int::class.java.classLoader) as? Int
        confirm_ordering = parcel.readValue(Int::class.java.classLoader) as? Int
        unsuccessfull_payment = parcel.readValue(Int::class.java.classLoader) as? Int
        delivery = parcel.readValue(Int::class.java.classLoader) as? Int
        success = parcel.readValue(Int::class.java.classLoader) as? Int
        cancel = parcel.readValue(Int::class.java.classLoader) as? Int
        created_at = parcel.readString()
        updated_at = parcel.readString()
        arrBooks = parcel.createTypedArrayList(BookOrder)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(code)
        parcel.writeValue(user_id)
        parcel.writeString(address)
        parcel.writeValue(payment)
        parcel.writeValue(confirm_ordering)
        parcel.writeValue(unsuccessfull_payment)
        parcel.writeValue(delivery)
        parcel.writeValue(success)
        parcel.writeValue(cancel)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeTypedList(arrBooks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }

}
