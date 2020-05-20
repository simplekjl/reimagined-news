package com.simplekjl.news.domain

import com.simplekjl.news.data.local.News
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable

//
// Created by simplekjl on 5/19/20.
//
interface NewsRepository {
    fun clearNews(): Completable
    fun saveNewsRaw(article: News): Completable
    fun getTopNews(page: Int): Observable<NewsEntity>
}
