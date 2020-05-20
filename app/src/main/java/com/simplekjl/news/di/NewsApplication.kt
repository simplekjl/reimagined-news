package com.simplekjl.news.di

import android.app.Application
import androidx.room.Room
import com.simplekjl.news.BuildConfig
import com.simplekjl.news.data.NewsService
import com.simplekjl.news.data.local.NewsAppDatabase
import com.simplekjl.news.data.local.NewsDao
import com.simplekjl.news.data.remote.Network
import com.simplekjl.news.data.remote.NetworkImpl
import com.simplekjl.news.domain.NewsRepository
import com.simplekjl.news.domain.NewsRepositoryImpl
import com.simplekjl.news.domain.interactors.RetrieveTopNews
import com.simplekjl.news.domain.mapper.NewsMapper
import com.simplekjl.news.presentation.news.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Class for DI with Koin
 * Created by simplekjl on 5/19/20.
 */
class NewsApplication : Application() {
    val appModule = module {
        single<Retrofit> {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(0, TimeUnit.SECONDS)
            builder.connectTimeout(5, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                builder.addInterceptor(interceptor)
            }

            val client = builder.build()

            Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
        }
        single<NewsAppDatabase> {
            Room.databaseBuilder(
                applicationContext,
                NewsAppDatabase::class.java, "news-db"
            ).build()
        }
        single<NewsDao> { get<NewsAppDatabase>().newsDao() }
        single<NewsService> { get<Retrofit>().create(NewsService::class.java) }
        factory<Network> { NetworkImpl(get()) }
        factory { NewsMapper() }
        factory<NewsRepository> { NewsRepositoryImpl(get(), get(),get()) }
        factory { RetrieveTopNews(get()) }
        viewModel { MainViewModel(get()) }

    }

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)
            modules(appModule)
        }
    }
}
