package com.app.bookselling.utils

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

class SessionManager @Inject
constructor(context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    var appVersion: String
        get() = pref.getString(APP_VERSION, Constants.EMPTY_STRING)
        set(appVersion) {
            editor.putString(APP_VERSION, appVersion)
            editor.apply()
        }

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
        editor.apply()
    }

    fun clear() {
        editor.remove(APP_VERSION)
        editor.apply()
    }

    companion object {

        private val APP_VERSION = "app_version"

        private val PREF_NAME = "book_shared_pref"
        private var instance: SessionManager? = null

        fun getInstance(context: Context?): SessionManager {
            if (instance == null) {
                instance = SessionManager(context!!)
            }
            return instance as SessionManager
        }
    }

}
