package com.app.vogobook.localstorage.typeconverter

import androidx.room.TypeConverter
import com.app.vogobook.utils.BookOrder
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


class BookOrderTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<BookOrder> {
        if (data == null) {
            return emptyList()
        }
        val listType: Type = object : TypeToken<List<BookOrder?>?>() {}.type
        return gson.fromJson<List<BookOrder>>(data, listType)
    }

    @TypeConverter
    fun ListToString(someObjects: List<BookOrder?>?): String {
        return gson.toJson(someObjects)
    }
}