package com.app.vogobook.utils

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SessionManager @Inject constructor(context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    var token: String

        get() = pref.getString(ACCESS_TOKEN, Constants.EMPTY_STRING)
        set(token) {
            editor.putString(ACCESS_TOKEN, token)
            editor.apply()
        }
    var time_using: Long
        get() = pref.getLong(TIME_USING_APP, 0)
        set(time_using) {
            editor.putLong(TIME_USING_APP, time_using)
            editor.apply()
        }

    var user_id: Int
        get() = pref.getInt(USER_ID, 0)
        set(user_id) {
            editor.putInt(USER_ID, user_id)
            editor.apply()
        }

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
        editor.apply()
    }

    fun clear() {
        editor.remove(ACCESS_TOKEN) // Clear any key you want, here clear ACCESS_TOKEN
        editor.remove(TIME_USING_APP)
        editor.apply()
    }

    companion object {
        private val ACCESS_TOKEN = "access_token"
        private val USER_ID = "user_id"
        private val PREF_NAME = "book_shared_pref"
        private val TIME_USING_APP = "time_using_app"
    }

}
