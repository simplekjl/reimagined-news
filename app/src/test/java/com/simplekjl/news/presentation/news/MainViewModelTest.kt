package com.simplekjl.news.presentation.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.news.RxImmediateSchedulerRule
import com.simplekjl.news.domain.interactors.RetrieveTopNews
import com.simplekjl.news.domain.model.NewsEntity
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    private val reviewsMock = mock<NewsEntity>()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLiveData = InstantTaskExecutorRule()

    @Mock
    lateinit var mockLiveDataObserver: Observer<Any>

    private var interactor: RetrieveTopNews = mock()

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(interactor)
    }

    @Test
    fun `subscribe to live data and get {Loading, ShowReviews}`() {
        whenever(interactor.getTopNews(any()))
            .thenReturn(Observable.just(reviewsMock))

        viewModel.uiStateLiveData.observeForever(mockLiveDataObserver)
        viewModel.getNews(any())
        verify(mockLiveDataObserver).onChanged(UiState.Loading)
        verify(mockLiveDataObserver).onChanged(UiState.OnSuccessNews(reviewsMock))
    }

    @Test
    fun `subscribe to live data and get {Loading, Error`() {
        whenever(interactor.getTopNews(0))
            .thenReturn(Observable.error(Throwable()))

        viewModel.uiStateLiveData.observeForever(mockLiveDataObserver)
        viewModel.getNews(0)
        verify(mockLiveDataObserver).onChanged(UiState.Loading)
        verify(mockLiveDataObserver).onChanged(UiState.Error)
    }
}