package com.example.myapplication.domain.interactor

import com.example.myapplication.Constants
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.repository.ApiRepository
import com.example.myapplication.data.repository.DbRepository
import com.example.myapplication.data.repository.SharedPreferencesRepository
import java.util.*
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val SharedPreferencesRepository: SharedPreferencesRepository,
    private val ApiRepository: ApiRepository,
    private val DbRepository: DbRepository
) : Interactor {
    override fun setTime(): Long? {
        var time: Long? = 0
        val editor = SharedPreferencesRepository.timeEditor()
        if (!SharedPreferencesRepository.created()) {//запустили впервые
            editor.putString(Constants.APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()
        } else {//обновили время
            time = SharedPreferencesRepository.timeSetting()
            editor.putString(Constants.APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()

        }
        return time
    }

    override suspend fun setRezults(): List<ListItemData> {
        val joblist = ApiRepository.getApiRezults()
        return joblist.map { it.toListItemData() }
    }

    override suspend fun setRezultsList() {
        DbRepository.insertPersFromApi(ApiRepository.getApiRezults())
    }

    override fun getPeers(): List<ListItemData> {
        return DbRepository.insertPers().map { it.toListItemData() }
    }

}