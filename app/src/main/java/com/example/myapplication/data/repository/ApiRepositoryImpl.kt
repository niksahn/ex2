package com.example.myapplication.data.repository


import com.example.myapplication.data.api.RickApi
import com.example.myapplication.data.model.rezults
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response


class ApiRepositoryImpl(private val rickApi: RickApi) : ApiRepository {
    override suspend fun getApiRezults(): List<Response<rezults>> {
        val k = 42
        val jobList = mutableListOf<Deferred<Response<rezults>>>()
        for (i in 1 until k) {
            withContext(Dispatchers.IO) {
                jobList.add(async { rickApi.getData(i.toString()).execute() })
            }
        }
        val listOfResults = jobList.mapNotNull {
            runCatching {
                it.await()
            }.getOrNull()
        }
        return listOfResults
    }

}


