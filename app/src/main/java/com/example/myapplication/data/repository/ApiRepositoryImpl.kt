package com.example.myapplication.data.repository


import com.example.myapplication.data.api.RickApi
import com.example.myapplication.data.model.ListItemDataDto
import com.example.myapplication.data.model.rezultsDto
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class ApiRepositoryImpl @Inject constructor(private val rickApi: RickApi) : ApiRepository {
    override suspend fun getApiRezults(): List<ListItemDataDto> {
        val k = 42
        val jobList = mutableListOf<Deferred<Response<rezultsDto>>>()
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

        return listOfResults.let { responseList ->
            responseList.mapNotNull { it.body()?.ListItemDataDto?.toList() }
                .reduceRightOrNull { cur, acc ->
                    acc + cur
                }?.sortedBy { it.id } ?: emptyList()
        }
    }

}


