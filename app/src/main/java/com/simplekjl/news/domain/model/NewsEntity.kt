package com.simplekjl.news.domain.model

//
// Created by simplekjl on 5/19/20.
//

data class NewsEntity(
    val status: String,
    val code: String,
    val message: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)