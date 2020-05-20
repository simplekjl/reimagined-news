package com.simplekjl.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.simplekjl.news.data.model.NewsResponseRaw
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): News

    @Query("SELECT * FROM news WHERE page IN (:page)")
    fun loadNewsByPage(page: Int): News

    @Insert
    fun insertAll(vararg news: News): Completable

    @Query("DELETE FROM news")
    fun delete(): Completable

}