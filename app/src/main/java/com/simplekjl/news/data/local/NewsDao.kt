package com.simplekjl.news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.simplekjl.news.data.model.NewsResponseRaw


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): NewsResponseRaw

    @Query("SELECT * FROM news WHERE page IN (:page)")
    fun loadNewsByPage(page: Int): NewsResponseRaw

    @Insert
    fun insertAll(vararg news: NewsResponseRaw)

    @Delete
    fun delete(news: NewsResponseRaw)

}