package com.app.vogobook.utils.objects

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.app.vogobook.BuildConfig

object Utils {

    fun isInternetOn(context: Context?): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    enum class LogType {
        INFO,ERROR,DEBUG
    }

    fun showLog(type: LogType, tag: String?, message: String) {
        if (BuildConfig.LOG)
            if (type == LogType.INFO) {
                Log.i(tag, message)
            } else if (type == LogType.ERROR){
                Log.e(tag, message)
            } else {
                Log.d(tag, message)
            }
    }

    fun replaceAvitityByScreen(name: String) : String {
        return name.substring(0,name.indexOf("Activity"))+"Screen"
    }

    fun replaceFragmentByScreen(name: String) : String {
        return name.substring(0,name.indexOf("Fragment"))+"Screen"
    }

}