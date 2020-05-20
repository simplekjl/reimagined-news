package com.simplekjl.news.domain.interactors

import com.simplekjl.news.domain.NewsRepository
import io.reactivex.Completable

//
// Created by  on 5/19/20.
//

class ClearCachedNews(private val repository: NewsRepository) {
    fun clearCachedNews(): Completable = repository.clearNews()
}