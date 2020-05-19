package com.simplekjl.news.data.remote

import com.simplekjl.news.data.model.NewsResponseRaw
import io.reactivex.Observable


interface Network {
    fun getTopNews(page: Int): Observable<NewsResponseRaw>
}
