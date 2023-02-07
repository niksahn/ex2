package com.example.myapplication.ui.inf

import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.ui.list.ListEvent
import com.example.myapplication.ui.list.ListState
import com.example.myapplication.utils.base.BaseViewModel

class InfViewModel(private val interactor: Interactor): BaseViewModel<InfState, InfEvent>(
    InfState()
) {

fun getData() {
    launchViewModelScope {
    updateState {
        it.copy(values=(interactor.getPeers()[screenState.value.id]).mapToValueOfPerson())
    }}
}
    fun onBack(){
        trySendEvent(InfEvent.goBack)
    }

}