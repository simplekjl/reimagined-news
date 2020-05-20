package com.simplekjl.news.presentation.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.news.R
import com.simplekjl.news.domain.model.Article
import com.squareup.picasso.Picasso

class ArticleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.new_item_layout, parent, false)) {
    // variables inside the item layout
    private var newImage: ImageView? = null
    private var newsCardContainer: CardView? = null
    private var newTitle: TextView? = null
    private var newContent: TextView? = null
    private var newDate: TextView? = null
    private var newSource: TextView? = null

    init {
        newImage = itemView.findViewById(R.id.newImage)
        newsCardContainer = itemView.findViewById(R.id.newsCardContainer)
        newTitle = itemView.findViewById(R.id.newTitle)
        newContent = itemView.findViewById(R.id.newContent)
        newDate = itemView.findViewById(R.id.newDate)
        newSource = itemView.findViewById(R.id.newSource)
    }

    fun setItem(article: Article) {
        Picasso.get()
            .load(article.urlToImage)
            .placeholder(R.drawable.image_place_holder)
            .error(R.drawable.image_place_holder)
            .into(newImage)
        newTitle?.text = article.title
        newContent?.text = article.content
        newDate?.text = article.publishedAt
        newSource?.text = article.source.name
    }
}