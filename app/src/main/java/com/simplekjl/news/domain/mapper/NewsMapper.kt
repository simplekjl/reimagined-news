package com.simplekjl.news.domain.mapper

import com.simplekjl.news.data.local.News
import com.simplekjl.news.data.model.ArticlesRaw
import com.simplekjl.news.data.model.NewsResponseRaw
import com.simplekjl.news.domain.model.Article
import com.simplekjl.news.domain.model.NewsEntity
import com.simplekjl.news.domain.model.Source
import java.time.ZonedDateTime
import java.util.*

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

    fun toNewsEntityFromStorage(news: News): NewsResponseRaw {
        return NewsResponseRaw(
            status = news.status,
            code = news.code,
            message = news.message,
            totalResults = news.totalResult,
            articles = news.articles
        )
    }

    fun toNewsStorageEntity(raw: NewsResponseRaw, page: Int): News {
        return News(
            page = page,
            status = raw.status,
            code = raw.code,
            message = raw.message,
            totalResult = raw.totalResults,
            articles = raw.articles
        )
    }

    private fun getArticlesEntity(rawList: ArrayList<ArticlesRaw>): List<Article> {
        val articleList = mutableListOf<Article>()
        rawList.forEach {
            val created = getFormatedDate(it.publishedAt)
            articleList.add(
                Article(
                    source = Source(it.source.id ?: "", it.source.name ?: ""),
                    author = it.author ?: "",
                    title = it.title ?: "",
                    description = it.description ?:" No description available",
                    url = it.url,
                    urlToImage = it.urlToImage,
                    publishedAt = created,
                    content = it.content ?: ""
                )
            )
        }
        return articleList
    }

    private fun getFormatedDate(created: String): String {
        val date = ZonedDateTime.parse(created)
        val difference = (ZonedDateTime.now().toLocalDateTime().hour - date.toLocalDateTime().hour)
        return "$difference hours ago"
    }
}