package com.simplekjl.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//
// Created by  on 5/20/20.
//

@Database(entities = [News::class], version = 1)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}