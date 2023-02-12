package com.example.myapplication.ui.inf

import com.example.myapplication.data.model.PressedButton
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class InfViewModel @Inject constructor(private val interactor: Interactor) :

    BaseViewModel<InfState, InfEvent>(
        InfState()
    ) {

    fun getData() {
        launchViewModelScope {
            updateState {
                it.copy(values = (interactor.getPeers()[PressedButton.id]).mapToValueOfPerson())
            }
        }
    }

    fun onBack() {
        trySendEvent(InfEvent.goBack)
    }

}