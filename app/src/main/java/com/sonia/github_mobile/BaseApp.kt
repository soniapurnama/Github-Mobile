package com.sonia.github_mobile

import android.app.Application
import com.sonia.github_mobile.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp :Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            androidLogger()
            modules(listOf(appModule))
        }
    }
}