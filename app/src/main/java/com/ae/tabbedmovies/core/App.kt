package com.ae.tabbedmovies.core

import android.app.Application
import com.ae.tabbedmovies.core.di.appModule
import com.ae.tabbedmovies.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                listOf(
                    appModule,
                    viewModelModule
                )
            )
        }
    }
}