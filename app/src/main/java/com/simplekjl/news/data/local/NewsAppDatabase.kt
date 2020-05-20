package com.simplekjl.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.simplekjl.news.data.local.converters.Converters

//
// Created by  on 5/20/20.
//

@Database(entities = [News::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}