package com.simplekjl.news.di

import com.simplekjl.news.data.NewsService
import com.simplekjl.news.data.remote.Network
import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.mapper.NewsMapper
import com.simplekjl.news.presentation.news.MainViewModel
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.category.CheckModuleTest
import org.koin.test.inject
import retrofit2.Retrofit


@Category(CheckModuleTest::class)
internal class NewsApplicationTest : KoinTest {
    val retrofit by inject<Retrofit>()
    val service by inject<NewsService>()
    val network by inject<Network>()
    val mapper by inject<NewsMapper>()
    val repository by inject<NewsRepository>()
    val viewModel by inject<MainViewModel>()

    @get: Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(NewsApplication().appModule)
    }


    @Test
    fun
            ` retrofit instance `() {
        assertNotNull(retrofit)
    }

    @Test
    fun
            ` service instance `() {
        assertNotNull(service)
    }

    @Test
    fun
            ` network instance `() {
        assertNotNull(network)
    }

    @Test
    fun
            ` mapper instance `() {
        assertNotNull(mapper)
    }

    @Test
    fun
            ` repository instance `() {
        assertNotNull(repository)
    }

    @Test
    fun
            `MainViewModel  instance `() {
        assertNotNull(viewModel)
    }
}