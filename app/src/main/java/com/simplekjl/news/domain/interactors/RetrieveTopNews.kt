package com.simplekjl.news.domain.interactors

import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Observable

class RetrieveTopNews(private val newsRepository: NewsRepository) {

    fun getTopNews(page: Int): Observable<NewsEntity> = newsRepository.getTopNews(page)
}