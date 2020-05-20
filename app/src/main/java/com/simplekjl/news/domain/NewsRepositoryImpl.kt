package com.simplekjl.news.domain

import com.simplekjl.news.data.local.News
import com.simplekjl.news.data.local.NewsDao
import com.simplekjl.news.data.remote.Network
import com.simplekjl.news.domain.mapper.NewsMapper
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Observable

//
// Created by simplekjl on 5/19/20.
//

class NewsRepositoryImpl(
    private val network: Network,
    private val mapper: NewsMapper,
    private val newsDb: NewsDao
) : NewsRepository {
    override fun clearNews(): Completable = newsDb.delete()

    override fun saveNewsRaw(news: News): Completable = newsDb.insertAll(news)

    override fun getTopNews(page: Int): Observable<NewsEntity> =
        network.getTopNews(page).doOnError {

        }.map {
            if (it.status == "ok") {

                // TODO map to News Entity
                // TODO map first before inserting
                val entityToStore = mapper.toNewsStorageEntity(it, page)
                newsDb.insertAll(entityToStore)
                mapper.toNewsEntity(it)
            } else {

                // TODO map to db entity
                val raw = mapper.toNewsEntityFromStorage(newsDb.getAllNews())
                mapper.toNewsEntity(raw)
            }
        }


}