package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ListItemDataDto
import com.example.myapplication.data.model.ListItemDataEntity

interface DbRepository {
    fun insertPers(): List<ListItemDataEntity>
    suspend fun insertPersFromApi(Apilist: List<ListItemDataDto>)
}