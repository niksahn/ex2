package com.example.myapplication.data.repository

import com.example.myapplication.data.model.ListItemDataDto

interface ApiRepository {
    suspend fun getApiRezults(): List<ListItemDataDto>
}