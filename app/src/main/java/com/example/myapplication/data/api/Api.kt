package com.example.myapplication.data.api


import com.example.myapplication.data.model.rezultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickApi {
    @GET("/api/character")
    fun getData(
        @Query("page") id: String?,
    ): Call<rezultsDto>
}




