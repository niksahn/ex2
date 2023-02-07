package com.example.myapplication.ui.list

import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.utils.base.State

data class ListState(
    var listOfItems: List<ListItem> = listOf(),
    var isLoading: Boolean = true
) : State()

data class ListItem(
    var id: Int = 0,
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var image: String? = null,
)
fun ListItemData.mapToListItem() =
    ListItem(
        name = name,
        status = status,
        species = species,
        image = image
    )