package com.simplekjl.news.domain.interactors

import com.simplekjl.news.data.local.News
import com.simplekjl.news.domain.NewsRepository
import io.reactivex.Completable

//
// Created by  on 5/19/20.
//

class StoreNewInCache(private val newsRepository: NewsRepository) {

    fun storeNewInCache(news: News): Completable = newsRepository.saveNewsRaw(news)
}