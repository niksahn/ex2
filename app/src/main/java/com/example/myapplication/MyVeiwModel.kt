package com.example.myapplication



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import androidx.test.core.app.ApplicationProvider.getApplicationContext

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface RickApi {
    @GET("/api/character")
    fun getData(
        @Query("page") id: String?,
        ): Call<rezults>
}


fun rickapi():RickApi{
    val baseurl = "https://rickandmortyapi.com"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(RickApi::class.java)}



@Database(
    entities = [ListItemData::class ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val personDao: PersonDao
}


class MyViewModel : ViewModel( ) {

    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()
    init {
        ///generate()
        //name== db.personDao.allPeople
       // println(name)
        generate()
       // db.personDao.insertAll(name)
       // name== db.personDao.allPeople
       // println(name)
    }
    private fun generate() = viewModelScope.launch(Dispatchers.IO) {
        supervisorScope {
            val rickApi = rickapi()
            val k = 42
            val jobList = mutableListOf<Deferred<Response<rezults>>>()
            for (i in 1 until k) {
                jobList.add(async { rickApi.getData(i.toString()).execute() })
            }
            val listOfResults = jobList.mapNotNull {
                runCatching {
                    it.await()
                }.getOrNull()
            }
            name.postValue(listOfResults.let { responseList ->
                responseList.mapNotNull { it.body()?.ListItemData?.toList() }
                    .reduceRightOrNull() { cur, acc ->
                        acc + cur
                    }?.sortedBy { it.id } ?: emptyList()
            } )
        }
    }}






