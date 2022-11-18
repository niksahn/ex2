package com.example.myapplication.Main.VeiwModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.model.rezults
import com.example.myapplication.main.Interactor
import com.example.myapplication.main.InteractorImpl
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.*



class MyViewModel(private val interactor: Interactor) : ViewModel() {

    private var time: Long? = 0
    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()

    init {
        time=interactor.setTime()
        val t = time ?: 0.toLong()
        if ((t == 0.toLong()) || (Date().time - t > 3600 * 1000)) {// впервые/час прошёл
            makingNameFromApi()
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {
                    name.postValue(interactor.insertPeers())
                }
            }
        }

    }

    private fun makingNameFromApi() =
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                name.postValue(interactor.setRezults())
               interactor.setRezultsList()
            }
        }


}








