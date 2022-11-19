package com.example.myapplication.data.model


import com.google.gson.annotations.SerializedName


data class rezultsDto(

    @SerializedName("info") var info: InfoDto? = InfoDto(),
    @SerializedName("results") var ListItemDataDto: ArrayList<ListItemDataDto> = arrayListOf()

)


data class InfoDto(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("pages") var pages: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null

)

data class OriginDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)

data class LocationDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null

)


class ListItemDataDto(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("species") var species: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null,

    @SerializedName("origin") var origin: OriginDto? = OriginDto(),

    @SerializedName("location") var location: LocationDto? = LocationDto(),
    @SerializedName("image") var image: String? = null,

    @SerializedName("episode") var episode: ArrayList<String>? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("created") var created: String? = null


) {
    fun toListItemDataEntity() =
        ListItemDataEntity(id, name, status, species, type, gender, image, url, created)

    fun toListItemData() =
        ListItemData(id, name, status, species, type, gender, image, url, created)
}

