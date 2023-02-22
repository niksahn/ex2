package com.example.myapplication.ui.list

import com.example.myapplication.utils.base.Event

sealed class ListEvent : Event() {
    class GoToInf(val index: Int) : ListEvent()
}