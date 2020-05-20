package com.simplekjl.news.data.remote

import com.simplekjl.news.data.NewsService
import com.simplekjl.news.data.model.NewsResponseRaw
import io.reactivex.Observable


class NetworkImpl(private val service: NewsService) : Network {
    override fun getTopNews(page: Int): Observable<NewsResponseRaw> = service.getTopHeadLines()
}