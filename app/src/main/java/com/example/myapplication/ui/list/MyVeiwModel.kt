package com.example.myapplication.ui.veiwModel


import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.ui.list.ListEvent
import com.example.myapplication.ui.list.ListItem
import com.example.myapplication.ui.list.ListState
import com.example.myapplication.ui.list.mapToListItem
import com.example.myapplication.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class MyViewModel @Inject constructor(private val interactor: Interactor) :
    BaseViewModel<ListState, ListEvent>(
        ListState()
    ) {

    private var time: Long? = 0

    init {
        println("KKK")
        time = interactor.setTime()
        val t = time ?: 0.toLong()
        if ((t == 0.toLong()) || (Date().time - t > 3600 * 100)) {// впервые/час прошёл
            makingNameFromApi()
        } else {
            launchViewModelScope {

                addNames(interactor.getPeers().map { it.mapToListItem() })
            }

        }

    }

    private fun makingNameFromApi() =
        launchViewModelScope {
            addNames(interactor.setRezults().map { it.mapToListItem() })
            interactor.setRezultsList()
        }

    private fun addNames(list: List<ListItem>) {
        updateState {
            it.copy(
                listOfItems = list,
                isLoading = false
            )
        }
    }

    fun onClickItem(index: Int) {
        trySendEvent(ListEvent.GoToInf(index))
    }
    fun searching(name: String)
    {  var list: ArrayList<ListItem> = arrayListOf()
        for (i in screenState.value.listOfItems)
        {  var m=i
            m.visible = i.name?.contains(name) != false
            list.add(m)
        }
        updateState {
           it.copy(
               listOfItems = list,
               searching = name
           )
        }
    }

}








