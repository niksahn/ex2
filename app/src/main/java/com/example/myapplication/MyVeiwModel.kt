package com.example.myapplication



import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.concurrent.thread


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
val APP_PREFERENCES = "mysettings"
val APP_PREFERENCES_TIME = "time_cash"
//var mSettings: SharedPreferences? = null

@RequiresApi(Build.VERSION_CODES.O)
class MyViewModel(mSettings: SharedPreferences, db: AppDatabase) : ViewModel() {
    var time:Long?=0
    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()
    init {
        val editor: SharedPreferences.Editor = mSettings.edit()
        if (!mSettings.contains(APP_PREFERENCES_TIME)){//запустили впервые
            editor.putString(APP_PREFERENCES_TIME, Date().time.toString())
            editor.commit()

        }
        else {//обновили время
            time= mSettings.getString(APP_PREFERENCES_TIME, "")?.toLong()
            editor.clear()
            editor.putString(APP_PREFERENCES_TIME, Date().time.toString())
            editor.commit()

        }
        var t=time ?: 0.toLong()
        if ((t==0.toLong())|| (Date().time -t  >3600*1000)){// впервые/час прошёл

            generate(db)}

        else{
    viewModelScope.launch(Dispatchers.IO) {
        supervisorScope {
            name.postValue( db.personDao.getUsers())

        }}
        }

    }
    private fun generate(db: AppDatabase) = viewModelScope.launch(Dispatchers.IO) {
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
            db.personDao.insertUser(listOfResults.let { responseList ->
                responseList.mapNotNull { it.body()?.ListItemData?.toList() }
                    .reduceRightOrNull() { cur, acc ->
                        acc + cur
                    }?.sortedBy { it.id } ?: emptyList()
            } )
        }
    }}



///generate
//()
//name== db.personDao.allPeople
// println(name)

// db.personDao.insertAll(name)
// name== db.personDao.allPeople
// println(name)






