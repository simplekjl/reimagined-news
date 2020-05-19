package com.simplekjl.news.domain.mapper

import com.simplekjl.news.data.model.ArticlesRaw
import com.simplekjl.news.data.model.NewsResponseRaw
import com.simplekjl.news.domain.model.Article
import com.simplekjl.news.domain.model.NewsEntity
import com.simplekjl.news.domain.model.Source

//
// Created by  on 5/19/20.
//

class NewsMapper {

    fun toNewsEntity(raw: NewsResponseRaw): NewsEntity {
        val articlesMapped: List<Article> = getArticlesEntity(raw.articles)
        return NewsEntity(
            status = raw.status,
            code = raw.code ?: "",
            message = raw.message ?: "",
            totalResults = raw.totalResults,
            articles = articlesMapped
        )
    }

    private fun getArticlesEntity(rawList: ArrayList<ArticlesRaw>): List<Article> {
        val articleList = mutableListOf<Article>()
        rawList.forEach {
            articleList.add(
                Article(
                    source = Source(it.source.id ?: "", it.source.name),
                    author = it.author ?: "",
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlToImage = it.urlToImage,
                    publishedAt = it.publishedAt,
                    content = it.content ?: ""
                )
            )
        }
        return articleList
    }
}