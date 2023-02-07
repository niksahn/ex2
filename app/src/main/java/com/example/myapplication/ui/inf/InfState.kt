package com.example.myapplication.ui.inf

import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.ui.list.ListItem
import com.example.myapplication.utils.base.State

data class InfState(

    var values: ValueOfPerson=ValueOfPerson(),
    var id: Int = 0

):State()
data class ValueOfPerson(
    var name: String? = null,
    var status: String? = null,
    var species: String? = null,
    var type: String? = null,
    var gender: String? = null,
    var image: String? = null,
    var url: String? = null,
    var created: String? = null
)
fun ListItemData.mapToValueOfPerson() =

        ValueOfPerson(
        name = name,
        status = status,
        species = species,
        type =type,
        gender=gender,
        url=url,
        image = image,
        created=created

    )
