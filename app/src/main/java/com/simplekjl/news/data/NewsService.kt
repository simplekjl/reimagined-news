package com.simplekjl.news.data

import com.simplekjl.news.BuildConfig
import com.simplekjl.news.data.model.NewsResponseRaw
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//
// Created by  on 5/19/20.
//

interface NewsService {
    // values fixed at the moment, future development can allow to set place and other parameters
    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 21,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ): Observable<NewsResponseRaw>
}