package com.example.myapplication.ui.list

import com.example.myapplication.utils.base.State

data class ListState(
    var lisOfItems: ArrayList<ListItem> = arrayListOf(),
    var isLoading: Boolean = true
) : State()

data class ListItem(
    var id: Int = 0,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
)