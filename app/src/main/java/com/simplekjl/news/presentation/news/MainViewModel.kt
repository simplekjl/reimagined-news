package com.simplekjl.news.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import com.simplekjl.news.data.remote.Network
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val network: Network) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getNews() {
        // TODO Remove to update for repository
        compositeDisposable.add(
            network.getTopNews(1).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(
                    {
                        Log.d("Getting news status: ", it.status)
                        Log.d("Getting news articles size: ", it.articles.size.toString())
                        Log.d("Getting news total results: ", it.totalResults.toString())
                    },
                    { Log.d("Error", "something went wrong") })
        )
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
