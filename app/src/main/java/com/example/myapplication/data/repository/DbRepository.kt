package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.model.rezults
import retrofit2.Response

interface DbRepository {
    fun insertPers(): List<ListItemData>
    suspend fun insertPersFromApi(Apilist: List<Response<rezults>>)
}