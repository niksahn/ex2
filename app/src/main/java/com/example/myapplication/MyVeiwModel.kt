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

//https://rickandmortyapi.com/api/character/?name=rick
class MyViewModel : ViewModel() {
    var name: MutableLiveData<ArrayList<ListItemData>> = MutableLiveData()

    init {
            generate()
            name.value?.sortBy {it.id}
    }

    private  fun generate() {
        val rickApi = rickapi()
        var k = 42
        var names = ArrayList<ListItemData>()
        for (i in 1 until k) {
            rickApi.getData(i.toString()).enqueue(object : Callback<rezults> {
                override fun onResponse(call: Call<rezults>, response: Response<rezults>) {
                    names +=  response.body()?.ListItemData ?: ArrayList<ListItemData>()
                    name.value = names
                }
                override fun onFailure(call: Call<rezults>, t: Throwable) {
                }
            })
        }
        /*fun getUsers(): Flow<ArrayList<ListItemData>> = flow {
            var names = ArrayList<ListItemData>()
            var response = rickApi.getData("1").execute()
            k = response.body()!!.info!!.pages!!
            println(k)
            for (i in 1 until k) {
                response = rickApi.getData(i.toString()).execute()
                names += response.body()!!.ListItemData
            }
            emit(names)
        }.flowOn(Dispatchers.Default)
        getUsers().collect { names -> name.value = names }*/

    }

}
