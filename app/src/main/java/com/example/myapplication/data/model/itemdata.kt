package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class rezults(

    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("results") var ListItemData: ArrayList<ListItemData> = arrayListOf()

)


data class Info(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null

)

data class Origin(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class Location(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

@Entity(tableName = "users")
data class ListItemData(
    @PrimaryKey @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null,

    @Ignore @SerializedName("origin") var origin: Origin? = Origin(),

    @Ignore @SerializedName("location") var location: Location? = Location(),
    @SerializedName("image") var image: String? = null,

    @Ignore @SerializedName("episode") var episode: ArrayList<String>? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null

)

