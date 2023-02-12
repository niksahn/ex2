package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ListItemDataDto
import com.example.myapplication.data.room.AppDatabase
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val db: AppDatabase) : DbRepository {
    override fun insertPers() = db.personDao.getUsers()

    override suspend fun insertPersFromApi(Apilist: List<ListItemDataDto>) {

        db.personDao.insertUser(Apilist.let { responseList ->
            responseList.mapNotNull { it.toListItemDataEntity() }
                .sortedBy { it.id }
        })
    }

}