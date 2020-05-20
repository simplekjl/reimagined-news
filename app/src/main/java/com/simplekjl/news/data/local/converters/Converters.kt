package com.simplekjl.news.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simplekjl.news.data.model.ArticlesRaw


//
// Created by  on 5/20/20.
//

open class Converters {
    @OptIn(ExperimentalStdlibApi::class)
    @TypeConverter
    fun fromString(value: String?): ArrayList<ArticlesRaw> {
        val listType = object : TypeToken<List<ArticlesRaw>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<ArticlesRaw?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}