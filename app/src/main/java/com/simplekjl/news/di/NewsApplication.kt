package com.simplekjl.news.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Class for DI with Koin
 * Created by simplekjl on 5/19/20.
 */
class NewsApplication : Application() {
    val appModule = module {

        // single instance of HelloRepository
//        single<HelloRepository> { HelloRepositoryImpl() }
//
        // Simple Presenter Factory
//        factory { MySimplePresenter(get()) }
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
