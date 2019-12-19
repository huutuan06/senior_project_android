package com.app.vogobook.utils.objects

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.app.vogobook.BuildConfig
import com.app.vogobook.R
import com.app.vogobook.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

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

    enum class Metrics {
        WIDTH, HEIGH
    }

    fun getWidth(activity: Activity, type: Metrics): Int {
        var size = 0
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        if (type == Metrics.WIDTH) size = displayMetrics.widthPixels
        if (type == Metrics.HEIGH) size = displayMetrics.heightPixels
        return size
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(context: Context, timestamp: Long?) : String {
        var calendar : Calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp!!*1000

        val simpleDateFormat = SimpleDateFormat(context!!.getString(R.string.partten_birthday_local))
        return simpleDateFormat.format(calendar.time)
    }

    fun hiddenKeyBoard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun enclosePercentage(keyword: String): String {
        return Constants.PERCENTAGE+(keyword)+(Constants.PERCENTAGE)
    }

}