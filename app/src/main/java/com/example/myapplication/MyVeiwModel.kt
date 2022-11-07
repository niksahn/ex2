package com.example.myapplication


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import retrofit2.Callback
import retrofit2.Response

val history= ArrayList<String>()
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
class MyViewModel : ViewModel() {
    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()
    init {
            generate()
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






