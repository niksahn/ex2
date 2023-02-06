package com.example.myapplication.ui.veiwModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.ui.list.ListEvent
import com.example.myapplication.ui.list.ListState
import com.example.myapplication.utils.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.*


class MyViewModel(private val interactor: Interactor) : BaseViewModel<ListState, ListEvent>(
    ListState()
) {

    private var time: Long? = 0
    var name: MutableLiveData<List<ListItemData>> = MutableLiveData()

    init {
        time = interactor.setTime()
        val t = time ?: 0.toLong()
        if ((t == 0.toLong()) || (Date().time - t > 3600)) {// впервые/час прошёл
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








