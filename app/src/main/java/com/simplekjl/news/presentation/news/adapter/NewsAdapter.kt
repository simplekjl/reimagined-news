package com.simplekjl.news.presentation.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.news.domain.model.Article

class NewsAdapter : RecyclerView.Adapter<ArticleViewHolder>() {

    private var articleList = mutableListOf<Article>()

    fun updateNews(newList: List<Article>) {
        articleList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(inflater, parent)

    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.setItem(articleList[position])
    }

}

