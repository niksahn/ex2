package com.example.myapplication.data.repository

import com.example.myapplication.data.model.rezults
import retrofit2.Response

interface ApiRepository {
    suspend fun getApiRezults(): List<Response<rezults>>
}