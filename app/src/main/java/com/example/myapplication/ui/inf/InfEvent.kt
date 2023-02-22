package com.example.myapplication.ui.inf

import com.example.myapplication.utils.base.Event

sealed class InfEvent : Event() {
    object goBack : InfEvent()
}