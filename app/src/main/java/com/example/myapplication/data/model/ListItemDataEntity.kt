package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class ListItemDataEntity(
 @PrimaryKey var id: Int = 0,
 var name: String? = null,
 var status: String? = null,
 var species: String? = null,
 var type: String? = null,
 var gender: String? = null,
 var image: String? = null,
 var url: String? = null,
 var created: String? = null

) {
 fun toListItemData() = ListItemData(id, name, status, species, type, gender, image, url, created)
}

