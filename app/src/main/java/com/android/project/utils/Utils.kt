package com.android.project.utils

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun isInternetOn(context: Context?): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}