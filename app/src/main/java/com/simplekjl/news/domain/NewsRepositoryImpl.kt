package com.simplekjl.news.domain

import com.simplekjl.news.data.remote.Network
import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.mapper.NewsMapper
import com.simplekjl.news.domain.model.Article
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

//
// Created by simplekjl on 5/19/20.
//

class NewsRepositoryImpl(
    private val network: Network,
    private val mapper : NewsMapper
) : NewsRepository {
    override fun clearNews(): Completable {
        // TODO add cache
        return Completable.complete()
    }

    override fun saveNew(article: Article): Completable {
        // TODO add to cache
        return Completable.complete()
    }

    override fun getTopNews(page: Int): Observable<NewsEntity> =
        network.getTopNews(page).map{ mapper.toNewsEntity(it)}

}