package com.example.myapplication.ui.veiwModel


import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.ui.list.ListEvent
import com.example.myapplication.ui.list.ListItem
import com.example.myapplication.ui.list.ListState
import com.example.myapplication.ui.list.mapToListItem
import com.example.myapplication.utils.base.BaseViewModel
import java.util.*


class MyViewModel(private val interactor: Interactor) : BaseViewModel<ListState, ListEvent>(
    ListState()
) {

    private var time: Long? = 0

    init {
        println("KKK")
        time = interactor.setTime()
        val t = time ?: 0.toLong()
        if ((t == 0.toLong()) || (Date().time - t > 3600*100)) {// впервые/час прошёл
            makingNameFromApi()
        } else {
            launchViewModelScope {

                addNames(interactor.getPeers().map { it.mapToListItem() }) }

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
                listOfItems = list ,
                isLoading = false
            )
        }
    }
    fun onClickItem( index: Int){
          trySendEvent(ListEvent.GoToInf(index))
    }
}








