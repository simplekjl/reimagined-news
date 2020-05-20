package com.simplekjl.news.presentation.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _uiStateLiveData = MutableLiveData<UiState>()
    val uiStateLiveData: LiveData<UiState>
        get() = _uiStateLiveData
    private val compositeDisposable = CompositeDisposable()
    private var totalResults = 1
    private var currentPage = 0

    fun getNews(itemCount: Int) {
        if (itemCount < totalResults) {
            compositeDisposable.add(
                retrieveTopNews.getTopNews(currentPage)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                        {
                            _uiStateLiveData.value = UiState.OnSuccessNews(it)
                            currentPage++
                            totalResults = it.totalResults
                            Log.d("Getting news status: ", it.status)
                            Log.d("Getting news articles size: ", it.articles.size.toString())
                            Log.d("Getting news total results: ", it.totalResults.toString())
                        },
                        {
                            _uiStateLiveData.value = UiState.Error
                            Log.d("Error", "something went wrong : ${it.message} ${it.stackTrace}")
                        },
                        { Log.d("Completed", "complete") },
                        { _uiStateLiveData.value = UiState.Loading }
                    )
            )
        }
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
