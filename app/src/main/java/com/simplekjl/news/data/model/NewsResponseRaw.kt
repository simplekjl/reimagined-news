package com.simplekjl.news.data.model

//
// Created by simplekjl on 5/19/20.
// Copyright (c) 2020 simplekjl . All rights reserved.
//

data class NewsResponseRaw(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Articles>
)

data class Articles(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String?,
    val name: String
)