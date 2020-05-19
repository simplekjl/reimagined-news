package com.simplekjl.news.domain.interactors

import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.model.Article
import io.reactivex.Completable

//
// Created by  on 5/19/20.
//

class StoreNewInCache(private val newsRepository: NewsRepository) {

    fun storeNewInCache(article: Article): Completable = newsRepository.saveNew(article)
}