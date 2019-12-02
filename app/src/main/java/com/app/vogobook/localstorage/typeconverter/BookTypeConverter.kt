package com.app.vogobook.localstorage.typeconverter

import androidx.room.TypeConverter
import com.app.vogobook.localstorage.entities.Book
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type


class BookTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Book> {
        if (data == null) {
            return emptyList<Book>()
        }
        val listType: Type = object : TypeToken<List<Book?>?>() {}.getType()
        return gson.fromJson<List<Book>>(data, listType)
    }

    @TypeConverter
    fun ListToString(someObjects: List<Book?>?): String {
        return gson.toJson(someObjects)
    }
}