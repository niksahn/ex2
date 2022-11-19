package com.example.myapplication.domain.interactor

import com.example.myapplication.data.model.ListItemData

interface Interactor {
    fun setTime(): Long?
    suspend fun setRezults(): List<ListItemData>
    suspend fun setRezultsList()
    fun insertPeers(): List<ListItemData>
}