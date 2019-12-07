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

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
        editor.apply()
    }

    fun clear() {
        editor.remove(ACCESS_TOKEN)
        editor.apply()
    }

    companion object {
        private val ACCESS_TOKEN = "access_token"
        private val PREF_NAME = "book_shared_pref"
    }

}
