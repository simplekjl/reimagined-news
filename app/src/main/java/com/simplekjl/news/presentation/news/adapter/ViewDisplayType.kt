package com.simplekjl.news.presentation.news.adapter

//
// Created by  on 5/19/20.
//

sealed class ViewDisplayType {
    object HeaderItem : ViewDisplayType()
    object GridItem : ViewDisplayType()

}