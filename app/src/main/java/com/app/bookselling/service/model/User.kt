package com.app.bookselling.service.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null

    @SerializedName("fake_avatar")
    @Expose
    var avatar: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("is_verified")
    @Expose
    var verify: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        email = parcel.readString()
        fullName = parcel.readString()
        avatar = parcel.readString()
        gender = parcel.readString()
        verify = parcel.readString()
        status = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(email)
        parcel.writeString(fullName)
        parcel.writeString(avatar)
        parcel.writeString(gender)
        parcel.writeString(verify)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}
