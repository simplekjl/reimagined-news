package com.simplekjl.news.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import com.simplekjl.news.domain.interactors.RetrieveTopNews
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * All the information from the data layer has to be retrieved by an interactor, this will allow us to
 * improve our scalability
 */
class MainViewModel(
    private val retrieveTopNews: RetrieveTopNews
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getNews() {
        // TODO Remove to update for repository
        compositeDisposable.add(
            retrieveTopNews.getTopNews(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {
                        Log.d("Getting news status: ", it.status)
                        Log.d("Getting news articles size: ", it.articles.size.toString())
                        Log.d("Getting news total results: ", it.totalResults.toString())
                    },
                    {
                        Log.d("Error", "something went wrong : ${it.message} ${it.stackTrace}")
                    }
                )
        )
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
