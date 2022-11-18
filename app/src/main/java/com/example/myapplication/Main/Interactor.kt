package com.example.myapplication.main

import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.data.model.rezults
import retrofit2.Response

interface Interactor {
    fun setTime(): Long?
    suspend fun setRezults():List<ListItemData>
    suspend fun setRezultsList()
    fun insertPeers(): List<ListItemData>
}