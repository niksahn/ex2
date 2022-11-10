package com.example.myapplication;

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Completable
import kotlin.collections.ArrayList

data class rezults (

    @SerializedName("info"    ) var info    : Info?              = Info(),
    @SerializedName("results" ) var ListItemData : ArrayList<ListItemData> = arrayListOf()

)


data class Info (

    @SerializedName("count" ) var count : Int?    = null,
    @SerializedName("pages" ) var pages : Int?    = null,
    @SerializedName("next"  ) var next  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null

)

data class Origin (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

data class Location (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null

)

@Entity(tableName = "users")
data class ListItemData(
    @PrimaryKey @SerializedName("id"       ) var id: Int              = 0,
    @SerializedName("name"     ) var name: String?           = null,
    @SerializedName("status"   ) var status: String?           = null,
    @SerializedName("species"  ) var species: String?           = null,
    @SerializedName("type"     ) var type: String?           = null,
    @SerializedName("gender"   ) var gender: String?           = null,

    @Ignore @SerializedName("origin"   ) var origin: Origin?           = Origin(),

    @Ignore@SerializedName("location" ) var location: Location?         = Location(),
    @SerializedName("image"    ) var image: String?           = null,

    @Ignore @SerializedName("episode"  ) var episode: ArrayList<String>? = null,
    @SerializedName("url"      ) var url: String?           = null,
    @SerializedName("created"  ) var created: String?           = null

)



@Dao
interface PersonDao {

    @Query("SELECT * FROM users")
    fun getUsers(): List<ListItemData>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<ListItemData>)
}























/*class Interconverted {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromHobbies(hobbies: ArrayList<java.lang.String>): String {
        var s=""
        for( i in 0 until hobbies.size) {s+=hobbies[i]; s+=","}
        return s
    }

    @TypeConverter
    fun toHobbies(data: String): ArrayList<Array<String>> {
        return arrayListOf(data.split(",".toRegex()).toTypedArray())
    }
}
class Converterorign {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromHobbies(hobbies: Origin? ): String {
        return hobbies?.name+","+hobbies?.url
    }

    @TypeConverter
    fun toHobbies(data: String): Origin? {
        var origin=Origin()
        origin.name=(data.split(",".toRegex())[0])
        origin.url=(data.split(",".toRegex())[1])
        return origin
    }
}
class Converterlocation {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromHobbies(hobbies: Location? ): String {
        return hobbies?.name+","+hobbies?.url
    }

    @TypeConverter
    fun toHobbies(data: String):Location? {
        var origin=Location()
        origin.name=(data.split(",".toRegex())[0])
        origin.url=(data.split(",".toRegex())[1])
        return origin
    }
}*/