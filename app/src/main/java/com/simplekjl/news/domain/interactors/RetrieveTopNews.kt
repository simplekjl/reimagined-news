package com.simplekjl.news.domain.interactors

import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Observable

//
// Created by  on 5/19/20.
//

class RetrieveTopNews(private val newsRepository: NewsRepository) {

    fun getTopNews(page: Int): Observable<NewsEntity> = newsRepository.getTopNews(page)
}