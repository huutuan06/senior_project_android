package com.app.vogobook.localstorage.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.vogobook.utils.Constants

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_USER)
class User() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.TABLE_USER_ID)
    @SerializedName("id")
    @Expose
    var id: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_NAME)
    @SerializedName("name")
    @Expose
    var name: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_EMAIL)
    @SerializedName("email")
    @Expose
    var email: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_PASSWORD)
    @SerializedName("password")
    @Expose
    var password: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_PHONE_NUMBER)
    @SerializedName("phone_number")
    @Expose
    var phone_number: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_BIRTH_DAY)
    @SerializedName("date_of_birth")
    @Expose
    var date_of_birth: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_GENDER)
    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_AVATAR)
    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_ADDRESS)
    @SerializedName("address")
    @Expose
    var address: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_IS_VERIFIED)
    @SerializedName("is_verified")
    @Expose
    var is_verified: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_REMEMBER_TOKEN)
    @SerializedName("remember_token")
    @Expose
    var remember_token: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_CREATED_AT)
    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @ColumnInfo(name = Constants.TABLE_USER_UPDATED_AT)
    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        email = parcel.readString()
        password = parcel.readString()
        phone_number = parcel.readString()
        date_of_birth = parcel.readString()
        gender = parcel.readString()
        avatar = parcel.readString()
        address = parcel.readString()
        is_verified = parcel.readString()
        remember_token = parcel.readString()
        created_at = parcel.readString()
        updated_at = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(phone_number)
        parcel.writeString(date_of_birth)
        parcel.writeString(gender)
        parcel.writeString(avatar)
        parcel.writeString(address)
        parcel.writeString(is_verified)
        parcel.writeString(remember_token)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
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
