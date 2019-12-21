package com.app.vogobook.service.model

import android.os.Parcel
import android.os.Parcelable
import com.app.vogobook.localstorage.entities.Cart
import com.app.vogobook.service.response.Address
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrdersData() : Parcelable {

    @SerializedName("carts")
    @Expose
    var carts: List<Cart> ?= null

    @SerializedName("user_id")
    @Expose
    var user_id: Int ?= null

    @SerializedName("address")
    @Expose
    var address: Address?= null

    constructor(parcel: Parcel) : this() {
        carts = parcel.createTypedArrayList(Cart)
        user_id = parcel.readValue(Int::class.java.classLoader) as? Int
        address = parcel.readParcelable(Address::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(carts)
        parcel.writeValue(user_id)
        parcel.writeParcelable(address, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrdersData> {
        override fun createFromParcel(parcel: Parcel): OrdersData {
            return OrdersData(parcel)
        }

        override fun newArray(size: Int): Array<OrdersData?> {
            return arrayOfNulls(size)
        }
    }
}