package com.simplekjl.news.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.simplekjl.news.data.model.ArticlesRaw


@Entity
data class News(
    @PrimaryKey val page: Int,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "code") val code: String?,
    @ColumnInfo(name = "message") val message: String?,
    @ColumnInfo(name = "totalResult") val totalResult: Int,
    @ColumnInfo(name = "articles") val articles: ArrayList<ArticlesRaw>

)