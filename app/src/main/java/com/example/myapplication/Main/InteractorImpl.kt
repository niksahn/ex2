package com.example.myapplication.main

import com.example.myapplication.Constants
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.repository.ApiRepository
import com.example.myapplication.data.repository.DbRepository
import com.example.myapplication.data.repository.SharedPreferencesRepository
import java.util.*

class InteractorImpl(private val SharedPreferencesRepository: SharedPreferencesRepository, private val ApiRepository: ApiRepository, private val DbRepository: DbRepository):Interactor {

    override fun setTime(): Long? {
        var time: Long? = 0
        val editor=SharedPreferencesRepository.timeEditor()
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
  override suspend fun setRezults():  List<ListItemData>{
    val joblist=ApiRepository.getApiRezults()
     return joblist.let { responseList ->
          responseList.mapNotNull { it.body()?.ListItemData?.toList() }
              .reduceRightOrNull() { cur, acc ->
                  acc + cur
              }?.sortedBy { it.id } ?: emptyList()
      }


  }
    override suspend fun setRezultsList() {
        DbRepository.insertPersFromApi(ApiRepository.getApiRezults())
    }

    override fun insertPeers(): List<ListItemData> {return DbRepository.insertPers()}

}