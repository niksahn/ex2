package com.example.myapplication

import android.app.Application
import com.example.myapplication.diModule.dataModule
import com.example.myapplication.diModule.domainModule
import com.example.myapplication.diModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, viewModelModule, domainModule))
        }
    }

}