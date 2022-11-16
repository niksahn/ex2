package com.example.myapplication.Main.VeiwModel



import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.api.RickApi
import com.example.myapplication.data.Room.AppDatabase
import com.example.myapplication.data.model.rezults
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.*






val APP_PREFERENCES = "mysettings"
val APP_PREFERENCES_TIME = "time_cash"

class MyViewModel(rickApi : RickApi, db: AppDatabase, mSettings: SharedPreferences) : ViewModel() {

    var time:Long?=0
    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()
    init {


        val editor: SharedPreferences.Editor = mSettings.edit()
        if (!mSettings.contains(APP_PREFERENCES_TIME)){//запустили впервые
            editor.putString(APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()

        }
        else {//обновили время
            time= mSettings.getString(APP_PREFERENCES_TIME, "")?.toLong()

            editor.putString(APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()

        }
        val t=time ?: 0.toLong()
        if ((t==0.toLong())|| (Date().time -t  >3600*1000)){// впервые/час прошёл

            generate(db,rickApi)}

        else{
    viewModelScope.launch(Dispatchers.IO) {
        supervisorScope {
            name.postValue( db.personDao.getUsers())

        }}
        }

    }

    private fun generate(db: AppDatabase,rickApi : RickApi) = viewModelScope.launch(Dispatchers.IO) {
        supervisorScope {

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
    }



}








