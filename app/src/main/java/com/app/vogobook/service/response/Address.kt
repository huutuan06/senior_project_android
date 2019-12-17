package com.app.vogobook.service.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address() : Parcelable {

    @SerializedName("name")
    @Expose
    var name: String ?= null

    @SerializedName("phone_number")
    @Expose
    var phone_number: String ?= null

    @SerializedName("address")
    @Expose
    var address: String ?= null

    @SerializedName("payment_method")
    @Expose
    var payment_method: String ?= null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        phone_number = parcel.readString()
        address = parcel.readString()
        payment_method = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone_number)
        parcel.writeString(address)
        parcel.writeString(payment_method)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}