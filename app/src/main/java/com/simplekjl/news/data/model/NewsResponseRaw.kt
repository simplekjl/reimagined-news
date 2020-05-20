package com.simplekjl.news.data.model

//
// Created by simplekjl on 5/19/20.
// Copyright (c) 2020 simplekjl . All rights reserved.
//

data class NewsResponseRaw(
    val status: String,
    val code: String?,
    val message: String?,
    val totalResults: Int,
    val articles: ArrayList<ArticlesRaw>
)

data class ArticlesRaw(
    val source: SourceRaw,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
)

data class SourceRaw(
    val id: String?,
    val name: String?
)