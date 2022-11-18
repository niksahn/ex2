package com.example.myapplication.data.repository

import com.example.myapplication.data.Room.AppDatabase
import com.example.myapplication.data.model.rezults
import retrofit2.Response

class DbRepositoryImpl(private val db: AppDatabase):DbRepository {
 override fun insertPers()=db.personDao.getUsers()
override suspend fun insertPersFromApi(Apilist: List<Response<rezults>>)
{ db.personDao.insertUser(Apilist.let { responseList ->
  responseList.mapNotNull { it.body()?.ListItemData?.toList() }
   .reduceRightOrNull() { cur, acc ->
    acc + cur
   }?.sortedBy { it.id } ?: emptyList()
 })}

}