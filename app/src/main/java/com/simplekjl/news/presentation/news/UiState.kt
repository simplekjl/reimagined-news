package com.simplekjl.news.presentation.news

import com.simplekjl.news.domain.model.NewsEntity

//
// Created by  on 5/19/20.
//

sealed class UiState {
    object Loading : UiState()
    data class OnSuccessNews(val data: NewsEntity) : UiState()
    object Error : UiState()
}