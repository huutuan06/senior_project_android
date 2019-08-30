package com.android.project.service.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

class Config : Parcelable {

    @SerializedName("app_version")
    @Expose
    var appVersion: String? = null

    constructor() {}

    constructor(appVersion: String) {
        this.appVersion = appVersion
    }

    protected constructor(`in`: Parcel) {
        appVersion = `in`.readString()
    }


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.appVersion)
    }

    companion object CREATOR : Parcelable.Creator<Config> {
        override fun createFromParcel(parcel: Parcel): Config {
            return Config(parcel)
        }

        override fun newArray(size: Int): Array<Config?> {
            return arrayOfNulls(size)
        }
    }
}
