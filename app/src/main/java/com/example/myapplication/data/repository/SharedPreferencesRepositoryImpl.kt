package com.example.myapplication.data.repository

import android.content.SharedPreferences
import com.example.myapplication.Constants
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(private val mSettings: SharedPreferences) :
    SharedPreferencesRepository {
    override fun timeEditor(): SharedPreferences.Editor {
        val editor: SharedPreferences.Editor = mSettings.edit()
        return editor
    }

    override fun created(): Boolean {
        return mSettings.contains(Constants.APP_PREFERENCES_TIME)
    }

    override fun timeSetting(): Long? {
        return mSettings.getString(Constants.APP_PREFERENCES_TIME, "")?.toLong()
    }

}
