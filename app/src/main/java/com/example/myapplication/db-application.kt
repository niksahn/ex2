package com.example.myapplication

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room


class App : Application() {
    companion object {
        var instance: App? = null
        var db: AppDatabase? = null
        var mSettings: SharedPreferences?=null
    }
    override fun onCreate() {
        instance = this
        db = Room.databaseBuilder(
            applicationContext ,
            AppDatabase::class.java, "pers.db"
        ).build()
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        super.onCreate()

    }

}